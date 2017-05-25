package com.mapbar.display.dto;

import java.io.Serializable;

/**
 * 集合入参DTO
 * Created by songzb on 2016/7/6.
 */
public class PolymerizeDto implements Serializable {

    // 纬度坐标
    private Double lat;
    // 经度坐标
    private Double lon;
    //聚合值为1时显示信息
    private String propertyInfo;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getPropertyInfo() {
        return propertyInfo;
    }

    public void setPropertyInfo(String propertyInfo) {
        this.propertyInfo = propertyInfo;
    }

    @Override
    public String toString() {
        return "PolymerizeDto{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", propertyInfo='" + propertyInfo + '\'' +
                '}';
    }
}
