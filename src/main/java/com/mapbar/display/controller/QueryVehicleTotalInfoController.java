package com.mapbar.display.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.display.command.TestCommand;
import com.mapbar.display.common.GenericResponseBody;
import com.mapbar.display.dto.GetTotalMileageAndPackage;
import com.mapbar.display.dto.LocationDataResp;
import com.mapbar.display.dto.QueryVehicleTotalInfo;
import com.mapbar.display.service.QueryVehicleTotalInfoService;
import com.mapbar.display.util.http.HttpEntity;
import com.mapbar.display.util.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjc on 2017/5/22.
 */
@Controller
@RequestMapping(value = "/totalInfo", method =
        { RequestMethod.GET,RequestMethod.POST }, produces =
        { "application/json;charset=UTF-8" })
public class QueryVehicleTotalInfoController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(QueryVehicleTotalInfoController.class);

    @Autowired
    private QueryVehicleTotalInfoService qvtiService;


    @RequestMapping(value = "/totalInfo")
    @GenericResponseBody
    public QueryVehicleTotalInfo totalInfo() throws Exception {
        HttpEntity<GetTotalMileageAndPackage> resp = HttpUtil.getLocalCloudJsonRequest("", new TypeReference<HttpEntity<GetTotalMileageAndPackage>>() {
        });
        GetTotalMileageAndPackage totalMil = resp.getBody();
        QueryVehicleTotalInfo totalInfo = new QueryVehicleTotalInfo();

        totalInfo.setCumulativeTotalMileage(String.valueOf(totalMil.getMileage()));//总里程数
        totalInfo.setOnlineVehicle(String.valueOf(totalMil.getOnlineNum()));//在线车辆
        totalInfo.setRunningVehicle(String.valueOf(totalMil.getDriveNum()));//行驶车辆
        totalInfo.setUploadDataCount(String.valueOf(totalMil.getPackageNum()));//上报数据
        totalInfo.setTotalVehicle(String.valueOf(qvtiService.getTotalVehicle()));//车辆总数

        return totalInfo;
    }
}
