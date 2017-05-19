package com.mapbar.display.dto;

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

    public void setCoordinates(double lat, double lot) {
        this.coordinates = new double[]{lat,lot};
    }
}
