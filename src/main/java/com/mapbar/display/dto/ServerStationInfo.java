package com.mapbar.display.dto;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 16:35
 */
public class ServerStationInfo {

    private String id;

    private String staName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    @Override
    public String toString() {
        return "ServerStationInfo{" +
                "id='" + id + '\'' +
                ", staName='" + staName + '\'' +
                '}';
    }
}
