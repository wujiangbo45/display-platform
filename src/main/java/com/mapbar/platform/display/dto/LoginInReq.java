package com.mapbar.platform.display.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by suixin on 2017/5/19.
 */
public class LoginInReq {

    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String username;	//	登录者名称

    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    private String password;	//	密码

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginInReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
