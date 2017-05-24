package com.mapbar.platform.display.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/24 11:17
 */
public class TokenReq {

    @NotNull(message = "token不能为空")
    @NotBlank(message = "token不能为空")
    protected String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
