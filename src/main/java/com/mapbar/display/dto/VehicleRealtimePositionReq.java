package com.mapbar.display.dto;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:46
 */
public class VehicleRealtimePositionReq {
    private String numBits;

    public String getNumBits() {
        return numBits;
    }

    public void setNumBits(String numBits) {
        this.numBits = numBits;
    }

    @Override
    public String toString() {
        return "VehicleRealtimePositionReq{" +
                "numBits='" + numBits + '\'' +
                '}';
    }
}
