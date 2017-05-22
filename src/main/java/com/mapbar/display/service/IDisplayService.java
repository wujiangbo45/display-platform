package com.mapbar.display.service;

import com.mapbar.display.dto.GetServiceStatisticsReq;
import com.mapbar.display.dto.GetServiceStatisticsResp;
import com.mapbar.display.dto.VehicleRealtimePositionReq;
import com.mapbar.display.dto.VehicleRealtimePositionResp;

import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:35
 */
public interface IDisplayService {

    public VehicleRealtimePositionResp getVehicleRealtimePosition(VehicleRealtimePositionReq req) throws Exception;

    public List<GetServiceStatisticsResp> getServiceStatistics(GetServiceStatisticsReq req) throws Exception;

}
