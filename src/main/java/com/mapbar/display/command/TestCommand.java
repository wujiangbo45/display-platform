package com.mapbar.display.command;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/19 14:14
 */
public class TestCommand {

    @NotBlank(message = "token不能为空")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
