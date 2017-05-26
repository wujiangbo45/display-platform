package com.mapbar.display.dto;

/**
 * Created by suixin on 2017/5/26.
 */
public class HyAccountDto {
    // 账号名
    private String accountname;

    // 用户密码
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
}
