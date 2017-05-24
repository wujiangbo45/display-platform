package com.mapbar.display.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by suixin on 2017/5/22.
 */
public class HyAccountDto extends BaseDTOV1 implements Serializable {
    // 账号名
    @JsonIgnore
    private String accountname;

    // 用户密码
    @JsonIgnore
    private String accountpwd;

    // 昵称
    private String realname;

    // 用户标记
    private String token;

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getAccountpwd() {
        return accountpwd;
    }

    public void setAccountpwd(String accountpwd) {
        this.accountpwd = accountpwd;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "HyAccountDto{" +
                "accountName='" + accountName + '\'' +
                ", accountNickName='" + accountNickName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
