package com.mapbar.display.dto;

import java.util.Arrays;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/19 13:17
 */
public class Geometry {

    private String type = "Point";

    private double [] coordinates;

    public String getType() {
        return type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    /**
     *
     * @param lon 经度
     * @param lat 纬度
     */
    public void setCoordinates(double lon, double lat) {
        this.coordinates = new double[]{lon,lat};
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
