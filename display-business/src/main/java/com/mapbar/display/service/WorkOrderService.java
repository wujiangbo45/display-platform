package com.mapbar.display.service;

import com.mapbar.common.utils.DateUtil;
import com.mapbar.display.dao.WorkOrderMapper;
import com.mapbar.display.dto.SelectWorkOrderStatus;
import com.mapbar.display.dto.ServiceWorkOrderResp;
import com.mapbar.display.dto.WorkOrderGroupByStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wujiangbo
 * @Create: 2017/09/30 上午10:53
 */
@Service
public class WorkOrderService {

    @Autowired
    WorkOrderMapper workOrderMapper;

    public ServiceWorkOrderResp getGroupByData(){

        // 当前时间
        String currentMonth = getCurrentMonthTime();
        List<WorkOrderGroupByStatus> list = workOrderMapper.groupByWorkOrderByStatus(currentMonth);

        ServiceWorkOrderResp resp = new ServiceWorkOrderResp();
        if (list != null && !list.isEmpty() && list.get(0) != null){
            WorkOrderGroupByStatus status = list.get(0);
            resp.setTotalWorkOrder(String.valueOf(status.getNum()));
            resp.setBeAssignedOrder(String.valueOf(status.getIndividualNum()));
            resp.setBePickedOrder(String.valueOf(status.getWaitingCarNum()));
            resp.setCheckingOrder(String.valueOf(status.getCheckInNum()));
            resp.setDoingServiceOrder(String.valueOf(status.getRepairNum()));
            resp.setBeConfirmedOrder(String.valueOf(status.getUnConfirmedNum()));
            resp.setWaitingOutStationOrder(String.valueOf(status.getWaitingStationNum()));
            resp.setOutStationOrder(String.valueOf(status.getExitStationNum()));
            resp.setClosedOrder(String.valueOf(status.getSystemClose()));
            // 获取外出救援数据
            // 获取预约出站
            // 获取自助进站
            List<SelectWorkOrderStatus> selectWorkOrderStatus = workOrderMapper.selectGroupByWoType(currentMonth, 1);
            Map<String,String> m1 = totalRecord(selectWorkOrderStatus);
            resp.setTodalOutService(m1.get("total"));
            resp.setOutService(m1.get("doingNum"));
            selectWorkOrderStatus = workOrderMapper.selectGroupByWoType(currentMonth, 2);
            m1 = totalRecord(selectWorkOrderStatus);
            resp.setTotalReservationOrder(m1.get("total"));
            resp.setReservationOrder(m1.get("doingNum"));
            selectWorkOrderStatus = workOrderMapper.selectGroupByWoType(currentMonth,3);
            m1 = totalRecord(selectWorkOrderStatus);
            resp.setTotalIndependentStation(m1.get("total"));
            resp.setIndependentStation(m1.get("doingNum"));
        }
        return resp;

    }

    private Map<String,String> totalRecord(List<SelectWorkOrderStatus> list){
        Map<String,String> m = new HashMap<>(2);
        int total = 0;
        SelectWorkOrderStatus status;
        if (list == null || list.isEmpty()){
            m.put("doingNum","0");
        }else{
            for (int i = 0; i < 2; i++) {
                status = list.get(i);
                total += Integer.parseInt(status.getNum().toString());
                if (1 == status.getSortType()) m.put("doingNum",status.getNum().toString());
            }
        }

        m.put("total",String.valueOf(total));
        return m;
    }


    private String getCurrentMonthTime(){
        return DateUtil.getFormatNowDate("yyyy-MM-01 00:00:00");
    }

}
