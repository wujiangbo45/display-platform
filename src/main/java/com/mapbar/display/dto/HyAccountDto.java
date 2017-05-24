package com.mapbar.display.dto;

import java.io.Serializable;

/**
 * Created by suixin on 2017/5/22.
 */
public class HyAccountDto implements Serializable {
    // 账号名
    private String accountName;

    // 昵称
    private String accountNickName;

    // 用户标记
    private String token;

    public String getAccountNickName() {
        return accountNickName;
    }

    public void setAccountNickName(String accountNickName) {
        accountNickName = accountNickName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        accountName = accountName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
