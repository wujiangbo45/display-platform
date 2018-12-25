package com.mapbar.report;

import com.mapbar.common.utils.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: wujiangbo
 * @Create: 2018/12/20 上午11:13
 */
@Component
public class Test {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    public void test(String startDate,String endDate){
        // 查询redis
        Map<Long,Long> joinMap = redisTemplate.execute((RedisCallback<Map<Long,Long>>) connection -> {
            Map<byte[], byte[]> all1 = connection.hGetAll("DA_TERMINAL_FIRST_RECIEVE".getBytes());
            Map<Long,Long> joinTimeMap = new HashMap<>();
            all1.forEach((key, value) -> joinTimeMap.put(Long.parseLong(new String(key)),
                    (long) byte2object(value)));
            return joinTimeMap;
        });
        String tripDaily = "TripDaily_";

        String city = "TerminalCity_";
        String[] dates = DateUtil.getDateArrayBetween(startDate, endDate, DateUtil.date_pattern);
        List<List<CountEntity>> tripList = new ArrayList<>();
        List<List<TerminalCityBean>> cityList = new ArrayList<>();
        for (String date : dates){
            String[] datesplit = date.split("-");
            String dateTail = datesplit[0] + datesplit[1];
            String tripDailyCollectionName = tripDaily + dateTail;
            String cityCollectionName = city + dateTail;
            Criteria tripcriteria = new Criteria();
            tripcriteria.andOperator(Criteria.where("date").is(date));
            Query query = new Query();
            query.addCriteria(tripcriteria);

            Criteria citycriteria = new Criteria();
            citycriteria.andOperator(Criteria.where("day").is(date));
            Query cityquery = new Query();
            cityquery.addCriteria(citycriteria);
            List<CountEntity> trips = mongoTemplate.find(query, CountEntity.class, tripDailyCollectionName);
            List<TerminalCityBean> terminalCityBeans = mongoTemplate.find(cityquery, TerminalCityBean.class, cityCollectionName);
            tripList.add(trips);
            cityList.add(terminalCityBeans);
        }
        // 里程油耗计算
        Map<Long,CountEntity> countEntityMap = tripList.parallelStream().flatMap(Collection::parallelStream)
                .collect(Collectors.groupingBy(CountEntity::getTerminalId))
                .entrySet()
                .parallelStream()
                .map(longListEntry -> {
                    List<CountEntity> countEntityList = longListEntry.getValue();
                    return countEntityList.stream().reduce((countEntity, countEntity2) -> {
                        countEntity.setmMilage(countEntity.getmMilage() + countEntity2.getmMilage());
                        countEntity.setFuel(countEntity.getFuel() + countEntity2.getFuel());
                        countEntity.setWorkHours(countEntity.getWorkHours() + countEntity2.getWorkHours());
                        return countEntity;
                    }).get();
                })
                .collect(Collectors.toMap(CountEntity::getTerminalId, o1-> o1))
                ;
        // 城市计算
        Map<Long,TerminalCityBean> terminalCityBeanMap = cityList.parallelStream()
                .flatMap(Collection::parallelStream)
                .collect(Collectors.groupingBy(TerminalCityBean::gettId))
                .entrySet()
                .parallelStream()
                .map(longListEntry -> {
                    TerminalCityBean newBean = new TerminalCityBean();
                    // 每辆车经过城市列表
                    List<TerminalCityBean> terminalCityBeans = longListEntry.getValue();
                    TerminalCityBean base = longListEntry.getValue().get(0);
                    List<DictInfo> dictInfoList = terminalCityBeans.stream()
                            .map(TerminalCityBean::getCityList)
                            .reduce((dictInfos, dictInfos2) -> {
                                dictInfos.addAll(dictInfos2);
                                return dictInfos;
                            }).get();
                    Map<Long,String> cityMap = new HashMap<>();
                    for (DictInfo dictInfo: dictInfoList) {
                        if (dictInfo != null) {
                            cityMap.put(dictInfo.getDictCode(),dictInfo.getName());
                        }
                    }
                    // 城市列表
                    Map<Long, Long> collect = dictInfoList.stream().collect(Collectors.groupingBy(DictInfo::getDictCode,
                                    Collectors.counting()));
                    // 前三名
                    Map<Long, Long> finalMap = new LinkedHashMap<>();
                    collect.entrySet().stream().sorted(Map.Entry.<Long,Long> comparingByValue().reversed()).
                            forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
                    // 排好序之后构造前三个城市数据
                    // 最东西南北数据
                    TerminalLocation east = terminalCityBeans.stream().map(TerminalCityBean::getEast).max(Comparator.comparingInt(TerminalLocation::getLon)).get();
                    TerminalLocation west = terminalCityBeans.stream().map(TerminalCityBean::getWest).min(Comparator.comparingInt(TerminalLocation::getLon)).get();
                    TerminalLocation south = terminalCityBeans.stream().map(TerminalCityBean::getSouth).min(Comparator.comparingInt(TerminalLocation::getLat)).get();
                    TerminalLocation north = terminalCityBeans.stream().map(TerminalCityBean::getNorth).max(Comparator.comparingInt(TerminalLocation::getLat)).get();
                    newBean.settId(longListEntry.getKey());
                    newBean.setCarChassisNum(base.getCarChassisNum());
                    newBean.setCarCph(base.getCarCph());
                    newBean.setEast(east);
                    newBean.setWest(west);
                    newBean.setSouth(south);
                    newBean.setNorth(north);
                    newBean.setCityList(finalMap.entrySet().stream().limit(3).map(longLongEntry -> {
                        DictInfo dictInfo = new DictInfo();
                        dictInfo.setDictCode(longLongEntry.getKey());
                        dictInfo.setName(cityMap.get(longLongEntry.getKey()));
                        return dictInfo;
                    }).collect(Collectors.toList()));

                    return newBean;
                }).collect(Collectors.toMap(TerminalCityBean::gettId, o1->o1))
                ;
        // 合并数据
        long current = System.currentTimeMillis()/1000;
        List<ReportTable> reportTables = joinMap.entrySet().stream().map(longLongEntry -> {
            Long tId = longLongEntry.getKey();
            Long time = (current - longLongEntry.getValue()) / 86400;
            CountEntity entity = countEntityMap.get(tId);
            TerminalCityBean bean = terminalCityBeanMap.get(tId);
            if (entity == null || bean == null) return null;
            ReportTable reportTable = new ReportTable();
            reportTable.settId(tId);
            reportTable.setCarCph(bean.getCarCph());
            reportTable.setCarChassisNum(bean.getCarChassisNum());
            BigDecimal mil = new BigDecimal(entity.getmMilage());
            BigDecimal fuel = new BigDecimal(entity.getFuel());
            reportTable.setmMilage(mil.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            reportTable.setFuel(fuel.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            reportTable.setWorkHours(entity.getWorkHours());
            if (entity.getmMilage() == 0) {
                reportTable.setAvgFuel(0);
            } else {
                reportTable.setAvgFuel(fuel.divide(mil, new MathContext(2, RoundingMode.HALF_DOWN)).multiply(new BigDecimal(100)).doubleValue());
            }
            reportTable.setNorth(bean.getNorth().getDictName());
            reportTable.setSouth(bean.getSouth().getDictName());
            reportTable.setEast(bean.getEast().getDictName());
            reportTable.setWest(bean.getWest().getDictName());

            reportTable.setCityList(bean.getCityList());
            reportTable.setJoinTime(time);
            return reportTable;
        }).filter(Objects::nonNull).collect(Collectors.toList());


        //excel标题
        String[] title = {"底盘号",
                "车牌号",
                "2018年行驶总里程\n（单位：KM）",
                "2018年行驶总油耗\n（单位:L）",
                "2018年行驶平均油耗\n（单位:L/百公里）",
                "2018年发动机总运行时间\n（单位：小时）",
                "2018年去过次数最多的城市top1",
                "2018年去过次数最多的城市top2",
                "2018年去过次数最多的城市top3",
                "2018年去过的最东边的地方",
                "2018年去过的最南边的地方",
                "2018年去过的最西边的地方",
                "2018年去过的最北边的地方",
                "车辆接入卡嘉车联网的总时间\n（单位：天）"};

        //excel文件名
        String fileName = "江淮年报"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "统计报表";
        String [][] content = new String[reportTables.size()][14];
        for (int i = 0; i < reportTables.size(); i++) {
            content[i] = new String[title.length];
            ReportTable obj = reportTables.get(i);
            content[i][0] = obj.getCarChassisNum();
            content[i][1] = obj.getCarCph();
            content[i][2] = Double.toString(obj.getmMilage());
            content[i][3] = Double.toString(obj.getFuel());
            content[i][4] = Double.toString(obj.getAvgFuel());
            content[i][5] = Double.toString(obj.getWorkHours());
            Iterator<DictInfo> it = obj.getCityList().iterator();
            if (it.hasNext()) content[i][6] = it.next().getName();
            if (it.hasNext()) content[i][7] = it.next().getName();
            if (it.hasNext()) content[i][8] = it.next().getName();
            content[i][9] = obj.getEast();
            content[i][10] = obj.getSouth();
            content[i][11] = obj.getWest();
            content[i][12] = obj.getNorth();
            content[i][13] = Long.toString(obj.getJoinTime());
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        try {
            OutputStream os = new FileOutputStream(fileName);
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }

    public static void main(String[] args) {
        String ab = "a,c";
        List<String> c = Stream.of(ab.split(",")).collect(Collectors.toList());
        Iterator<String> it = c.iterator();
        if (it.hasNext()) System.out.println(it.next());
        if (it.hasNext()) System.out.println(it.next());
        if (it.hasNext()) System.out.println(it.next());
    }

    public static Object byte2object(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(bais, ois);
        }
        return null;
    }
    private static void close(ByteArrayInputStream bais, ObjectInputStream ois) {
        if (bais != null) {
            try {
                bais.close();
            } catch (IOException e) {
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                    }
                }
            }
        } else {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
