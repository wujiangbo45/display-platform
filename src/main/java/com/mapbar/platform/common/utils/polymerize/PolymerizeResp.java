package com.mapbar.platform.common.utils.polymerize;

/**
 * 聚合输出结果
 * Created by songzb on 2016/7/6.
 */
public class PolymerizeResp {

    // 纬度坐标
    private Double lat;
    // 经度坐标
    private Double lon;
    // 聚合值
    private Integer carCount;
    //聚合值为1时返回特定信息
    private String propertyInfo;

    public String getPropertyInfo() {
        return propertyInfo;
    }

    public void setPropertyInfo(String propertyInfo) {
        this.propertyInfo = propertyInfo;
    }

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

    public Integer getCarCount() {
        return carCount;
    }

    public void setCarCount(Integer carCount) {
        this.carCount = carCount;
    }
}
