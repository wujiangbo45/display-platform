package com.mapbar.display.controller;

import com.mapbar.display.command.BaseCommand;
import com.mapbar.display.common.GenericResponseBody;
import com.mapbar.display.dto.Features;
import com.mapbar.display.dto.Geometry;
import com.mapbar.display.dto.VehicleRealtimePositionResp;
import com.mapbar.display.service.TdsService;
import com.mapbar.display.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinsihua on 2017/5/2.
 */
@Controller
@RequestMapping(value = "/tds", method =
        { RequestMethod.GET,RequestMethod.POST }, produces =
        { "application/json;charset=UTF-8" })
public class TdsController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(TdsController.class);

    @Autowired
    private TdsService tdsService;

    @RequestMapping(value = "/test")
    @GenericResponseBody
    public Map<String,String> test(BaseCommand command) throws Throwable {
        Map<String, String> result = new HashMap<String, String>();
        result.put("key", "hello, word!!!");
        tdsService.test();
        return result;
    }

    public static void main(String[] args) {
        VehicleRealtimePositionResp s = new VehicleRealtimePositionResp();
        List<Features> f = new ArrayList<>();
        Features d = new Features();
        Geometry g = new Geometry();
        g.setCoordinates(115.4,127.333);
        d.setGeometry(g);
        d.setProperties("3333");
        f.add(d);
        s.setFeatures(f);
        System.out.println(JsonUtils.toJson(s));
    }
}
