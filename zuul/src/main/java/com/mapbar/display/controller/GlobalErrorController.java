package com.mapbar.display.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangdong on 2017/6/23.
 */
@RestController
@RequestMapping("/error")
public class GlobalErrorController extends AbstractErrorController {
    private static Logger log = LoggerFactory.getLogger(GlobalErrorController.class);
    @Autowired
    public GlobalErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    public ResponseEntity error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if (status.is5xxServerError()) {
            Map re = getErrorAttributes(request, false);
            log.error("zuul eexception info:{}", re.toString());
            Map reMap = new HashMap();
            reMap.put("resultCode", status.value());
            reMap.put("message", re.get("exception"));
            return new ResponseEntity(reMap, status);
        }

        return new ResponseEntity(status);
    }

}
