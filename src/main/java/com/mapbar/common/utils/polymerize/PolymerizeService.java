package com.mapbar.common.utils.polymerize;

import com.mapbar.display.dto.PolymerizeDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 地图聚合服务
 * Created by songzb on 2016/7/5.
 */
@Service
public class PolymerizeService {

    private final char[] codes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    // 精度
//    private final Integer[] precision = {5,6,7,10,11,12,15,16,17,20,21};//one
//    private final Integer[] precision = {1,3,4,6,7,12,15,16,17,20,21};//two
//    private final Integer[] precision = {1,5,6,10,11,12,15,16,17,20,21};//three
    private final Integer[] precision = {3,4,6,10,11,12,13,15,16,17,20,21};//four
    // 区域距离长度
    private final Double[] kmLevel = {626.172135,150.348414,82.119366,19.567879,4.707179,2.564679,0.611496,0.147084,0.080188,0.019109,0.004596};

    private final double EARTH_RADIUS = 6378.137;

    public List<PolymerizeResp> getPolymerizeResult(List<PolymerizeDto> latLon, Integer numBits) {
        Set<String> setGeoHash = new HashSet<>();
        List<PolymerizeResp> polymerizeList = new ArrayList<PolymerizeResp>();
        HashMap<String,Integer> countMap = new HashMap<String, Integer>();
        HashMap<String,PolymerizeDto> lonLatSet = new HashMap<String, PolymerizeDto>();
        String strGeoHash = "";
        //2016/11/6 songzb start 增加在同一聚合区域中经纬度的平均值计算
//        //geohash值
//        for (int i = 0; i < latLon.size(); i++) {
//            strGeoHash = this.encode(latLon.get(i).getLat(), latLon.get(i).getLon(),numBits);
//            if(setGeoHash.add(strGeoHash)){
//                // 存储GeoHash值
//                countMap.put(strGeoHash,1);
//                lonLatSet.put(strGeoHash,latLon.get(i));
//            }else{
//                // 计算GeoHash数量
//                Integer iCount = countMap.get(strGeoHash);
//                countMap.put(strGeoHash, iCount + 1);
//                if (lonLatSet.containsKey(strGeoHash)) {
//                    lonLatSet.remove(strGeoHash);
//                }
//            }
//        }
//
//        //经纬度值
//        for(String setValue : setGeoHash){
//            PolymerizeResp result = new PolymerizeResp();
//            // 聚合值大于1时，聚合坐标
//            if (countMap.get(setValue) > 1) {
//                double[] flatlon = this.decode(setValue, numBits);
//                result.setLat(flatlon[0]);
//                result.setLon(flatlon[1]);
//                result.setCarCount(countMap.get(setValue));
//                polymerizeList.add(result);
//            }else if (countMap.get(setValue) == 1) {
//                // 聚合值为1时，车辆经纬度坐标
//                result.setLat(lonLatSet.get(setValue).getLat());
//                result.setLon(lonLatSet.get(setValue).getLon());
//                result.setCarCount(1);
//                result.setPropertyInfo(lonLatSet.get(setValue).getPropertyInfo());
//                polymerizeList.add(result);
//            }
//        }
        
        HashMap<String, List<PolymerizeDto>> lonLatRecordMap = new HashMap<String, List<PolymerizeDto>>();
        for (int i = 0; i < latLon.size(); i++) {
            strGeoHash = this.encode(latLon.get(i).getLat(), latLon.get(i).getLon(),numBits);
            if(setGeoHash.add(strGeoHash)){
                // 存储GeoHash值
                countMap.put(strGeoHash,1);
                lonLatSet.put(strGeoHash,latLon.get(i));
                //将在同一聚合区域中的点存储到以strGeoHash为key的map中
                List<PolymerizeDto> onlyList = new ArrayList<PolymerizeDto>();
                PolymerizeDto onlyObj = new PolymerizeDto();
                onlyObj.setLon(latLon.get(i).getLon());
                onlyObj.setLat(latLon.get(i).getLat());
                onlyObj.setPropertyInfo(latLon.get(i).getPropertyInfo());
                onlyList.add(onlyObj);
                lonLatRecordMap.put(strGeoHash, onlyList);
            }else{
                // 计算GeoHash数量
                Integer iCount = countMap.get(strGeoHash);
                countMap.put(strGeoHash, iCount + 1);
                if (lonLatSet.containsKey(strGeoHash)) {
                    lonLatSet.remove(strGeoHash);
                }
                //将在同一聚合区域中的点存储到以strGeoHash为key的map中
                List<PolymerizeDto> moreList = new ArrayList<PolymerizeDto>();
                moreList = lonLatRecordMap.get(strGeoHash);
                PolymerizeDto currentObj = new PolymerizeDto();
                currentObj.setLon(latLon.get(i).getLon());
                currentObj.setLat(latLon.get(i).getLat());
                currentObj.setPropertyInfo(latLon.get(i).getPropertyInfo());
                moreList.add(currentObj);
                lonLatRecordMap.put(strGeoHash, moreList);
            }
        }

        //经纬度值
        for(String setValue : setGeoHash){
            PolymerizeResp result = new PolymerizeResp();
            // 聚合值大于1时，聚合坐标
            if (countMap.get(setValue) > 1) {
            	//计算经纬度平均值
            	List<PolymerizeDto> moreList = new ArrayList<PolymerizeDto>();
                moreList = lonLatRecordMap.get(setValue);
                Double sumLon = 0.0;
                Double sumLat = 0.0;
                Double avgLon = 0.0;
                Double avgLat = 0.0;
                for (PolymerizeDto dto : moreList) {
                	sumLon += dto.getLon();
                	sumLat += dto.getLat();
				}
                avgLon = formatDouble(sumLon / moreList.size(), 5);
                avgLat = formatDouble(sumLat / moreList.size(), 5);
                result.setLon(avgLon);
                result.setLat(avgLat);
                result.setCarCount(countMap.get(setValue));
                polymerizeList.add(result);
            }else if (countMap.get(setValue) == 1) {
                // 聚合值为1时，车辆经纬度坐标
                result.setLat(lonLatSet.get(setValue).getLat());
                result.setLon(lonLatSet.get(setValue).getLon());
                result.setCarCount(1);
                result.setPropertyInfo(lonLatSet.get(setValue).getPropertyInfo());
                polymerizeList.add(result);
            }
        }
        //2016/11/6 songzb end 增加在同一聚合区域中经纬度的平均值计算
        return polymerizeList;
    }

    /**
     * 设置geohash精度
     *
     */
    public int setPrecision(double maxLat, double maxLon, double minLat, double minLon) {
        // 两点间距离
        double sAccuracy = getDistance(maxLat, minLon, minLat, minLon)/10;
        double sLon = getDistance(minLat, maxLon, minLat, minLon)/10;
        if (sAccuracy < sLon) {
            sAccuracy = sLon;
        }
        // 设置精度
        int i = 0;
        for (double ke : kmLevel) {
            if (i != kmLevel.length) {
                if (i == kmLevel.length - 1){
                    return precision[i];
                }
                // 确定需要比较的区域距离长度
                if ( sAccuracy > kmLevel[i + 1]) {
                    // 间距
                    double sc1 = Math.abs(sAccuracy-ke);
                    double sc2 = Math.abs(sAccuracy-kmLevel[i + 1]);
                    // 设定精度
                    if (sc1 < sc2) {
                        return precision[i];
                    } else if (sc1 >= sc2) {
                        return precision[i + 1];
                    }
                }
                i++;
            }
        }

        return precision[i-1];
    }
    /**
     * 经纬度间的距离
     *
     */
    private double getDistance(double maxLat, double maxLng, double minLat, double minLng)
    {
        double radLat1 = this.rad(maxLat);
        double radLat2 = this.rad(minLat);
        double a = radLat1 - radLat2;
        double b = this.rad(maxLng) - this.rad(minLng);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;
    }

    /**
     * 将Geohash字串解码成经纬值
     *
     * @param geohash 待解码的Geohash字串
     * @return 经纬值数组
     */
    private double[] decode(String geohash,int numbits) {
        HashMap<Character, Integer> codesMap = new HashMap<Character, Integer>();
        StringBuilder buffer = new StringBuilder();

        int ci = 0;
        for (char c : codes) {
            codesMap.put(c, ci++);
        }

        for (char c : geohash.toCharArray()) {
            int i = codesMap.get(c) + 32;
            buffer.append(Integer.toString(i, 2).substring(1));
        }

        for (int i = 0; i < buffer.length(); i++) {
            if (buffer.charAt(i) == '1') {
                buffer.delete(0,i);
                break;
            }
        }

        BitSet lonset = new BitSet();
        BitSet latset = new BitSet();

        // even bits
        int j = 0;
        for (int i = 0; i < numbits * 2; i += 2) {
            boolean isSet = false;
            if (i < buffer.length())
                isSet = buffer.charAt(i) == '1';
            lonset.set(j++, isSet);
        }

        // odd bits
        j = 0;
        for (int i = 1; i < numbits * 2; i += 2) {
            boolean isSet = false;
            if (i < buffer.length())
                isSet = buffer.charAt(i) == '1';
            latset.set(j++, isSet);
        }

        double lat = decode(latset, -90, 90);
        double lon = decode(lonset, -180, 180);

        DecimalFormat df = new DecimalFormat("0.000000");
        return new double[] { Double.parseDouble(df.format(lat)), Double.parseDouble(df.format(lon)) };
    }

    /**
     * 根据二进制编码串和指定的数值变化范围，计算得到经/纬值
     *
     * @param bs
     *            经/纬二进制编码串
     * @param floor
     *            下限
     * @param ceiling
     *            上限
     * @return 经/纬值
     */
    private double decode(BitSet bs, double floor, double ceiling) {
        double mid = 0;
        for (int i = 0; i < bs.length(); i++) {
            mid = (floor + ceiling) / 2;
            if (bs.get(i))
                floor = mid;
            else
                ceiling = mid;
        }
        return mid;
    }

    /**
     * 根据经纬值得到Geohash字串
     *
     * @param lat
     *            纬度值
     * @param lon
     *            经度值
     * @return Geohash字串
     */
    private String encode(double lat, double lon, int numbits) {
        BitSet latbits = getBits(lat, -90, 90,numbits);
        BitSet lonbits = getBits(lon, -180, 180,numbits);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < numbits; i++) {
            buffer.append((lonbits.get(i)) ? '1' : '0');
            buffer.append((latbits.get(i)) ? '1' : '0');
        }
        return base32(Long.parseLong(buffer.toString(), 2));
    }

    /**
     * 将二进制编码串转换成Geohash字串
     *
     * @param i
     *            二进制编码串
     * @return Geohash字串
     */
    private String base32(long i) {
        char[] buf = new char[65];
        int charPos = 64;
        boolean negative = (i < 0);
        if (!negative)
            i = -i;
        while (i <= -32) {
            buf[charPos--] = codes[(int) (-(i % 32))];
            i /= 32;
        }
        buf[charPos] = codes[(int) (-i)];

        if (negative)
            buf[--charPos] = '-';
        return new String(buf, charPos, (65 - charPos));
    }

    /**
     * 得到经/纬度对应的二进制编码
     *
     * @param lat
     *            经/纬度
     * @param floor
     *            下限
     * @param ceiling
     *            上限
     * @return 二进制编码串
     */
    private BitSet getBits(double lat, double floor, double ceiling,int numbits) {
        BitSet buffer = new BitSet(numbits);
        for (int i = 0; i < numbits; i++) {
            double mid = (floor + ceiling) / 2;
            if (lat >= mid) {
                buffer.set(i);
                floor = mid;
            } else {
                ceiling = mid;
            }
        }
        return buffer;
    }

    private double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    
    /**
     * 格式化double类型的精度
     * @param original
     * @return bitInt
     */
    private Double formatDouble(Double original, Integer bitInt) {
        if (null == original)
        {
            return new Double(0);
        }
        BigDecimal b = new BigDecimal(original);

        Double f = b.setScale(bitInt, BigDecimal.ROUND_HALF_UP).doubleValue();

        return f;
    }

    public static void main(String[] args) {
//        //===============================================================================
//        // 对车辆进行聚合
//        //===============================================================================
        PolymerizeService polymerizeService = new PolymerizeService();
        int numBits = polymerizeService.setPrecision(39.9305539204465,116.54424947580344,39.90544137998913,116.41376591150993);
        System.out.println(numBits);
//        if (realTimePointList != null && realTimePointList.size() > 0) {
//            //2016/11/6 songzb start 精度修改设定，当地图级别为17级及以上时，精度设置为最大
//            int numBits = 5;//默认值设定为精度最大值(精度最大值代表精度最低)
//            List<PolymerizeResp> polymerizeResultList = new ArrayList<PolymerizeResp>();
//            if (inDto.getLevel() != null && inDto.getLevel() >= 0 &&
//                    inDto.getLevel() <= 16){
//                // 精度设置
//                numBits = polymerizeService.setPrecision(inDto.getRightLatitude(), inDto.getRightLongitude(),
//                        inDto.getLeftLatitude(), inDto.getLeftLongitude());
//                //过滤可视屏幕区域内的点
//                List<PolymerizeDto> screenPointList = new ArrayList<PolymerizeDto>();
//                screenPointList = filterScreenCarToDto(realTimePointList, inDto);
//                // 聚合信息
//                polymerizeResultList = polymerizeService.getPolymerizeResult(screenPointList, numBits + 2);
//            }else{
//                polymerizeResultList = filterScreenCar(realTimePointList, inDto);
//            }
////                int numBits = polymerizeService.setPrecision(inDto.getRightLatitude(), inDto.getRightLongitude(),
////                        inDto.getLeftLatitude(), inDto.getLeftLongitude());
////            	// 聚合信息
////                List<PolymerizeResp> polymerizeResultList = polymerizeService.getPolymerizeResult(realTimePointList, numBits + 2);
//            //2016/11/6 songzb end
//            // dto转换
//            if (!polymerizeResultList.isEmpty()) {
//                carPolymerizeDtoList = pojoListToDtoList(polymerizeResultList);
//            }
//        }
//
    }
}
