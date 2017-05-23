package com.mapbar.display.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.display.common.UrlProperties;
import com.mapbar.display.common.bind.GenericResponseBody;
import com.mapbar.display.dto.*;
import com.mapbar.display.service.IDisplayService;
import com.mapbar.display.service.QueryVehicleTotalInfoService;
import com.mapbar.display.util.http.HttpUtil;
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
        totalInfo.setCumulativeTotalMileage(String.valueOf(totalMil.getMileage()));//总里程数
        totalInfo.setOnlineVehicle(String.valueOf(totalMil.getOnlineNum()));//在线车辆
        totalInfo.setRunningVehicle(String.valueOf(totalMil.getDriveNum()));//行驶车辆
        totalInfo.setUploadDataCount(String.valueOf(totalMil.getPackageNum()));//上报数据
        totalInfo.setTotalVehicle(String.valueOf(qvtiService.getTotalVehicle()));//车辆总数

        return totalInfo;
    }

}
