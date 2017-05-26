package com.mapbar.display.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by suixin on 2017/5/22.
 */
public class HyAccountResp {
    // 昵称
    private String realname;

    // 用户标记
    private String token;

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
        return "HyAccountResp{" +
                ", realname='" + realname + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
