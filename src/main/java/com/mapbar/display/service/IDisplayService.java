package com.mapbar.display.service;

import com.mapbar.display.dto.VehicleRealtimePositionReq;
import com.mapbar.display.dto.VehicleRealtimePositionResp;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:35
 */
public interface IDisplayService {

    public VehicleRealtimePositionResp getVehicleRealtimePosition(VehicleRealtimePositionReq req);
}
