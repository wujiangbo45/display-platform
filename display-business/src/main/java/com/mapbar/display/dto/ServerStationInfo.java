package com.mapbar.display.dto;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 16:35
 */
public class ServerStationInfo {


    private String staname;


    public String getStaname() {
        return staname;
    }

    public void setStaname(String staname) {
        this.staname = staname;
    }


    @Override
    public String toString() {
        return "ServerStationInfo{" +
                ", staname='" + staname + '\'' +
                '}';
    }
}
