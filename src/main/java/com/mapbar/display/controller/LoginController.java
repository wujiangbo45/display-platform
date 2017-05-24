package com.mapbar.display.controller;

import com.mapbar.display.command.LoginInCommand;
import com.mapbar.display.common.bind.GenericResponseBody;
import com.mapbar.display.dto.HyAccountDto;
import com.mapbar.display.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by suixin on 2017/5/19.
 */

@Controller
@RequestMapping(value = "/display", method =
        { RequestMethod.GET,RequestMethod.POST }, produces =
        { "application/json;charset=UTF-8" })
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login")
    @GenericResponseBody
    public HyAccountDto login(LoginInCommand command) {
        return loginService.login(command);
    }
}
