package com.mapbar.display.service;

import com.mapbar.display.dto.*;

import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:35
 */
public interface IDisplayService {

    public VehicleRealtimePositionResp getVehicleRealtimePosition(VehicleRealtimePositionReq req) throws Exception;

    public List<GetServiceStatisticsResp> getServiceStatistics(GetServiceStatisticsReq req) throws Exception;

    public HyAccountResp login(LoginInReq command) throws Exception;

    public int getTotalVehicle() throws Exception;
}
