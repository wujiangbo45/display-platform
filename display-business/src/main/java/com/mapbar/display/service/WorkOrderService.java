package com.mapbar.display.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.common.Const;
import com.mapbar.common.UrlProperties;
import com.mapbar.common.utils.DateUtil;
import com.mapbar.common.utils.JsonUtil;
import com.mapbar.common.utils.JsonUtils;
import com.mapbar.common.utils.StringUtil;
import com.mapbar.common.utils.http.HttpUtil;
import com.mapbar.common.utils.http.LocalCloudRespopnse;
import com.mapbar.common.web.bind.GenericResponse;
import com.mapbar.display.dao.WorkOrderMapper;
import com.mapbar.display.dto.SelectWorkOrderStatus;
import com.mapbar.display.dto.ServiceWorkOrderResp;
import com.mapbar.display.dto.WorkOrderGroupByStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: wujiangbo
 * @Create: 2017/09/30 上午10:53
 */
@Service
public class WorkOrderService {

    @Autowired
    WorkOrderMapper workOrderMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    public ServiceWorkOrderResp getGroupByData(){

        String userId = getUserId();
        List<String> userIdList = Arrays.stream(userId.replaceAll("'","").split(",")).collect(Collectors.toList());
        String workOrder = redisTemplate.opsForValue().get(Const.WORK_ORDER_DATA_KEY);
        ServiceWorkOrderResp resp = null;
        if (StringUtil.isNotEmpty(workOrder)){
            resp = JsonUtils.fromJson(redisTemplate.opsForValue().get(Const.WORK_ORDER_DATA_KEY), new TypeReference<ServiceWorkOrderResp>() {
            });
        }else{
            List<WorkOrderGroupByStatus> list = workOrderMapper.groupByWorkOrderByStatus(userIdList);
            if (list != null && !list.isEmpty() && list.get(0) != null){
                WorkOrderGroupByStatus workOrderGroupByStatus = list.get(0);
                resp = new ServiceWorkOrderResp();
                resp.setTotalWorkOrder(String.valueOf(workOrderGroupByStatus.getNum()));
                resp.setBeAssignedOrder(String.valueOf(workOrderGroupByStatus.getIndividualNum()));
                resp.setBePickedOrder(String.valueOf(workOrderGroupByStatus.getWaitingCarNum()));
                resp.setCheckingOrder(String.valueOf(workOrderGroupByStatus.getCheckInNum()));
                resp.setDoingServiceOrder(String.valueOf(workOrderGroupByStatus.getRepairNum()));
                resp.setBeConfirmedOrder(String.valueOf(workOrderGroupByStatus.getUnConfirmedNum()));
                resp.setWaitingOutStationOrder(String.valueOf(workOrderGroupByStatus.getWaitingStationNum()));
                // 已完成的
                resp.setDoneOrder(String.valueOf(workOrderGroupByStatus.getNum().intValue()
                        - workOrderGroupByStatus.getIndividualNum().intValue() - workOrderGroupByStatus.getWaitingCarNum().intValue()
                        - workOrderGroupByStatus.getCheckInNum().intValue() - workOrderGroupByStatus.getRepairNum().intValue()
                        - workOrderGroupByStatus.getUnConfirmedNum().intValue() - workOrderGroupByStatus.getWaitingStationNum().intValue()
                ));
                // 获取外出救援数据
                // 获取预约出站
                // 获取自助进站
                List<SelectWorkOrderStatus> selectWorkOrderStatus = workOrderMapper.selectGroupByWoType(userIdList, 1);
                Map<String,String> m1 = totalRecord(selectWorkOrderStatus);
                resp.setTodalOutService(m1.get("total"));
                resp.setOutService(m1.get("doingNum"));
                selectWorkOrderStatus = workOrderMapper.selectGroupByWoType(userIdList, 2);
                m1 = totalRecord(selectWorkOrderStatus);
                resp.setTotalReservationOrder(m1.get("total"));
                resp.setReservationOrder(m1.get("doingNum"));
                selectWorkOrderStatus = workOrderMapper.selectGroupByWoType(userIdList,3);
                m1 = totalRecord(selectWorkOrderStatus);
                resp.setTotalIndependentStation(m1.get("total"));
                resp.setIndependentStation(m1.get("doingNum"));
                redisTemplate.opsForValue().set(Const.WORK_ORDER_DATA_KEY, JsonUtils.toJson(resp), 3,TimeUnit.MINUTES);
            }
        }
        return resp;

    }

    private Map<String,String> totalRecord(List<SelectWorkOrderStatus> list){
        Map<String,String> m = new HashMap<>(2);
        int total = 0;
        int doingNum = 0;
        if (list != null && !list.isEmpty()){
            for (SelectWorkOrderStatus status: list) {
                total += Integer.parseInt(status.getNum().toString());
                if (1 == status.getSortType()) doingNum = status.getNum().intValue();
            }
        }
        m.put("doingNum",String.valueOf(total - doingNum));
        m.put("total",String.valueOf(total));
        return m;
    }


    private String getUserId(){
        String userId = redisTemplate.opsForValue().get("operate_user");
        if (StringUtil.isEmpty(userId)){
            userId = HttpUtil.getLocalCloudJsonRequest(UrlProperties.getUrl("operate.getDepart"), new TypeReference<LocalCloudRespopnse<String>>() {});
            redisTemplate.opsForValue().set("operate_user",userId, 30, TimeUnit.MINUTES);
        }
        return userId;
    }


    private String getCurrentMonthTime(){
        return DateUtil.getFormatNowDate("yyyy-MM-01 00:00:00");
    }

}
