package com.mapbar.display.controller;

import com.mapbar.display.command.TestCommand;
import com.mapbar.display.common.GenericResponseBody;
import com.mapbar.display.dto.VehicleRealtimePositionReq;
import com.mapbar.display.service.IDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String,String> queryVehicleRealtimePosition(VehicleRealtimePositionReq req) throws Exception {
//        command.getToken();
        service.getVehicleRealtimePosition(req);
        Map<String, String> result = new HashMap<String, String>();
        result.put("key", "hello, word!!!");
//        tdsService.test();
        return result;
    }
}
