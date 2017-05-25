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
