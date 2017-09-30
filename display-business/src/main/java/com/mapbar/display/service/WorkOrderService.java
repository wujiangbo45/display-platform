package com.mapbar.display.service;

import com.mapbar.display.dao.WorkOrderMapper;
import com.mapbar.display.dto.ServiceWorkOrderResp;
import com.mapbar.display.dto.WorkOrderGroupByStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/09/30 上午10:53
 */
@Service
public class WorkOrderService {

    @Autowired
    WorkOrderMapper workOrderMapper;

    public ServiceWorkOrderResp getGroupByData(){
        List<WorkOrderGroupByStatus> list = workOrderMapper.groupByWorkOrderByStatus();

        ServiceWorkOrderResp resp = new ServiceWorkOrderResp();
        if (list != null|| !list.isEmpty()){
            WorkOrderGroupByStatus status = list.get(0);
            resp.setTodalOutService("1000");
            resp.setOutService("500");
            resp.setTotalReservationOrder("1000");
            resp.setReservationOrder("500");
            resp.setTotalIndependentStation("1000");
            resp.setIndependentStation(String.valueOf("333"));
            resp.setTotalWorkOrder(String.valueOf(status.getNum()));
            resp.setBeAssignedOrder(String.valueOf(status.getIndividualNum()));
            resp.setBePickedOrder(String.valueOf(status.getWaitingCarNum()));
            resp.setCheckingOrder(String.valueOf(status.getCheckInNum()));
            resp.setDoingServiceOrder(String.valueOf(status.getRepairNum()));
            resp.setBeConfirmedOrder(String.valueOf(status.getUnConfirmedNum()));
            resp.setWaitingOutStationOrder(String.valueOf(status.getWaitingStationNum()));
            resp.setOutStationOrder(String.valueOf(status.getExitStationNum()));
            resp.setClosedOrder(String.valueOf(status.getSystemClose()));
        }
        return resp;



    }
}
