package com.mapbar.tds.controller;

import com.mapbar.tds.command.BaseCommand;
import com.mapbar.tds.command.TdsInCommand;
import com.mapbar.tds.command.TdsOutCommand;
import com.mapbar.tds.common.GenericResponseBody;
import com.mapbar.tds.result.CommonResult;
import com.mapbar.tds.service.TdsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
        return result;
    }

}
