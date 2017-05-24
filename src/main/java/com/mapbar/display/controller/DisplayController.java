package com.mapbar.display.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.display.command.LoginInCommand;
import com.mapbar.display.common.UrlProperties;
import com.mapbar.display.common.bind.GenericResponseBody;
import com.mapbar.display.dto.*;
import com.mapbar.display.service.IDisplayService;
import com.mapbar.display.service.LoginService;
import com.mapbar.display.service.QueryVehicleTotalInfoService;
import com.mapbar.display.util.http.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @Autowired
    QueryVehicleTotalInfoService qvtiService;

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

    @RequestMapping(value = "/queryVehicleTotalInfo")
    @GenericResponseBody
    public QueryVehicleTotalInfo totalInfo() throws Exception {
        String url = UrlProperties.getUrl("localcloud.getTotalMileageAndPackage");
        GetTotalMileageAndPackage totalMil = HttpUtil.getLocalCloudJsonRequest(url, new TypeReference<LocalCloudRespopnse<GetTotalMileageAndPackage>>() {
        });
        QueryVehicleTotalInfo totalInfo = new QueryVehicleTotalInfo();
        totalInfo.setCumulativeTotalMileage(String.valueOf(totalMil.getMileage()));
        totalInfo.setOnlineVehicle(String.valueOf(totalMil.getOnlineNum()));
        totalInfo.setRunningVehicle(String.valueOf(totalMil.getDriveNum()));
        totalInfo.setUploadDataCount(String.valueOf(totalMil.getPackageNum()));
        totalInfo.setTotalVehicle(String.valueOf(qvtiService.getTotalVehicle()));

        return totalInfo;
    }

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login")
    @GenericResponseBody
    public HyAccountDto login(LoginInCommand command) throws Exception{
        return loginService.login(command);
    }
}
