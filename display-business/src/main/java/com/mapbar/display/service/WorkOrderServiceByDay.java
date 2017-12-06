package com.mapbar.display.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.common.Const;
import com.mapbar.common.UrlProperties;
import com.mapbar.common.utils.DateUtil;
import com.mapbar.common.utils.JsonUtils;
import com.mapbar.common.utils.StringUtil;
import com.mapbar.common.utils.http.HttpUtil;
import com.mapbar.common.utils.http.LocalCloudRespopnse;
import com.mapbar.display.dao.WorkOrderMapper;
import com.mapbar.display.dto.SelectWorkOrderStatus;
import com.mapbar.display.dto.ServiceWorkOrderResp;
import com.mapbar.display.dto.WorkOrderGroupByStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: wujiangbo
 * @Create: 2017/12/06 上午10:34
 */
@Profile("byDay")
@Service
public class WorkOrderServiceByDay extends WorkOrderUtil implements IWorkOrderService{

    @Autowired
    WorkOrderMapper workOrderMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Value("${search.work.day}")
    private int day;

    @Override
    public ServiceWorkOrderResp getGroupByData() {
        String userId = getUserId();
        List<String> userIdList = Arrays.stream(userId.replaceAll("'","").split(",")).collect(Collectors.toList());
        String workOrder = redisTemplate.opsForValue().get(Const.WORK_ORDER_DATA_KEY_BY_DAY);
        ServiceWorkOrderResp resp = null;
        if (StringUtil.isNotEmpty(workOrder)){
            resp = JsonUtils.fromJson(redisTemplate.opsForValue().get(Const.WORK_ORDER_DATA_KEY_BY_DAY), new TypeReference<ServiceWorkOrderResp>() {
            });
        }

        if (resp == null){
            String eDate = DateUtil.getFormatNowDate("yyyy-MM-dd");
            String sDate = DateUtil.addDayReturnString(eDate,(day - 1) * -1);
            List<WorkOrderGroupByStatus> list = workOrderMapper.groupByWorkOrderByStatusByDay(userIdList,sDate, eDate);
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
                List<SelectWorkOrderStatus> selectWorkOrderStatus = workOrderMapper.selectGroupByWoTypeByDay(userIdList, 1,sDate, eDate);
                Map<String,String> m1 = totalRecord(selectWorkOrderStatus);
                resp.setTodalOutService(m1.get("total"));
                resp.setOutService(m1.get("doingNum"));
                selectWorkOrderStatus = workOrderMapper.selectGroupByWoTypeByDay(userIdList, 2, sDate, eDate);
                m1 = totalRecord(selectWorkOrderStatus);
                resp.setTotalReservationOrder(m1.get("total"));
                resp.setReservationOrder(m1.get("doingNum"));
                selectWorkOrderStatus = workOrderMapper.selectGroupByWoTypeByDay(userIdList,3, sDate, eDate);
                m1 = totalRecord(selectWorkOrderStatus);
                resp.setTotalIndependentStation(m1.get("total"));
                resp.setIndependentStation(m1.get("doingNum"));
                redisTemplate.opsForValue().set(Const.WORK_ORDER_DATA_KEY_BY_DAY, JsonUtils.toJson(resp), 3,TimeUnit.MINUTES);
            }
        }else{
            resp = new ServiceWorkOrderResp();
        }
        return resp;
    }

}
