package com.mapbar.display.dto;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:50
 */
public class LocationDataResp {
    /**
     * 纬度
     */
    private double lat;

    /**
     * 经度
     */
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
