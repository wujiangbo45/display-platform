package com.mapbar.display.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:46
 */
public class VehicleRealtimePositionReq {

    @NotNull(message = "地图精度不能为空")
    @NotBlank(message = "地图精度不能为空")
    @Pattern(regexp = "^\\d{1,2}$",message = "地图精度必须为小于或等于两位的数字")
    private String numBits;

    /**
     * 右经度（东北角）
     */
    private Double rightLongitude;

    /**
     * 右纬度（东北角）
     */
    private Double rightLatitude;

    /**
     * 左经度（西南角）
     */
    private Double leftLongitude;

    /**
     * 左纬度（西南角）
     */
    private Double leftLatitude;

    public Double getRightLongitude() {
        return rightLongitude;
    }

    public void setRightLongitude(Double rightLongitude) {
        this.rightLongitude = rightLongitude;
    }

    public Double getLeftLongitude() {
        return leftLongitude;
    }

    public void setLeftLongitude(Double leftLongitude) {
        this.leftLongitude = leftLongitude;
    }

    public Double getRightLatitude() {
        return rightLatitude;
    }

    public void setRightLatitude(Double rightLatitude) {
        this.rightLatitude = rightLatitude;
    }

    public Double getLeftLatitude() {
        return leftLatitude;
    }

    public void setLeftLatitude(Double leftLatitude) {
        this.leftLatitude = leftLatitude;
    }

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
                ", rightLongitude='" + rightLongitude + '\'' +
                ", leftLongitude='" + leftLongitude + '\'' +
                ", rightLatitude='" + rightLatitude + '\'' +
                ", leftLatitude='" + leftLatitude + '\'' +
                '}';
    }
}
