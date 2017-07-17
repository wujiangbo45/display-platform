package com.mapbar.display.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.common.UrlProperties;
import com.mapbar.common.aspect.LoginRequired;
import com.mapbar.common.utils.http.HttpUtil;
import com.mapbar.common.utils.http.LocalCloudRespopnse;
import com.mapbar.common.web.bind.GenericResponseBody;
import com.mapbar.display.dto.*;
import com.mapbar.display.service.IDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import com.mapbar.display.service.IDisplayService;

import javax.validation.Valid;
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
//
    @Autowired
    IDisplayService service;

    @RequestMapping(value = "/queryVehicleRealtimePosition")
    @GenericResponseBody
    @LoginRequired
    public VehicleRealtimePositionResp queryVehicleRealtimePosition(@Valid VehicleRealtimePositionReq req) throws Exception {
        VehicleRealtimePositionResp resp = service.getVehicleRealtimePosition(req);
        return resp;
    }


    @RequestMapping(value = "/queryServiceStatistics")
    @GenericResponseBody
    @LoginRequired
    public List<GetServiceStatisticsResp> queryServiceStatistics(@Valid GetServiceStatisticsReq req) throws Exception {
        return service.getServiceStatistics(req);
    }

    @RequestMapping(value = "/queryVehicleTotalInfo")
    @GenericResponseBody
    @LoginRequired
    public QueryVehicleTotalInfo totalInfo() throws Exception {
        String url = UrlProperties.getUrl("localcloud.getTotalMileageAndPackage");
        GetTotalMileageAndPackage totalMil = HttpUtil.getLocalCloudJsonRequest(url, new TypeReference<LocalCloudRespopnse<GetTotalMileageAndPackage>>() {
        });
        QueryVehicleTotalInfo totalInfo = new QueryVehicleTotalInfo();
        totalInfo.setCumulativeTotalMileage(String.valueOf(totalMil.getMileage()));
        totalInfo.setOnlineVehicle(String.valueOf(totalMil.getOnlineNum()));
        totalInfo.setRunningVehicle(String.valueOf(totalMil.getDriveNum()));
        totalInfo.setUploadDataCount(String.valueOf(totalMil.getPackageNum()));
        totalInfo.setTotalVehicle(String.valueOf(service.getTotalVehicle()));

        return totalInfo;
    }


    @RequestMapping(value = "/login",consumes = {"application/json","application/x-www-form-urlencoded"})
    @GenericResponseBody
    public HyAccountResp login(@RequestBody @Valid LoginInReq command) throws Exception{
        return service.login(command);
    }

    @RequestMapping(value = "/logout")
    @GenericResponseBody
    public void login(@Valid LogoutReq req) throws Exception{
        service.logOut(req);
    }
}
