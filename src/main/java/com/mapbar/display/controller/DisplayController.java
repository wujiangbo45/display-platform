package com.mapbar.display.controller;

import com.mapbar.display.common.bind.GenericResponseBody;
import com.mapbar.display.dto.GetServiceStatisticsReq;
import com.mapbar.display.dto.GetServiceStatisticsResp;
import com.mapbar.display.dto.VehicleRealtimePositionReq;
import com.mapbar.display.dto.VehicleRealtimePositionResp;
import com.mapbar.display.service.IDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:33
 */
@Controller
@RequestMapping(value = "/display", method =
        { RequestMethod.GET,RequestMethod.POST }, produces =
        { "application/json;charset=UTF-8" })
public class DisplayController {

    @Autowired
    IDisplayService service;

    @RequestMapping(value = "/queryVehicleRealtimePosition")
    @GenericResponseBody
    public VehicleRealtimePositionResp queryVehicleRealtimePosition(VehicleRealtimePositionReq req) throws Exception {
        VehicleRealtimePositionResp resp = service.getVehicleRealtimePosition(req);
        return resp;
    }


    @RequestMapping(value = "/queryServiceStatistics")
    @GenericResponseBody
    public List<GetServiceStatisticsResp> queryServiceStatistics(GetServiceStatisticsReq req) throws Exception {
        return service.getServiceStatistics(req);
    }


}
