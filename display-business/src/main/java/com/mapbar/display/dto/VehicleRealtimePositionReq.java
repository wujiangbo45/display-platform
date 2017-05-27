package com.mapbar.display.dto;

import com.mapbar.common.utils.RegexpUtils;
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
    @NotNull(message = "右经度（东北角）不能为空")
    @NotBlank(message = "右经度（东北角）不能为空")
    @Pattern(regexp = RegexpUtils.LON_REGEXP, message = "请填写正确的经度信息")
    private String rightLongitude;

    /**
     * 右纬度（东北角）
     */
    @NotNull(message = "右纬度（东北角）不能为空")
    @NotBlank(message = "右纬度（东北角）不能为空")
    @Pattern(regexp = RegexpUtils.LAT_REGEXP, message = "请填写正确的纬度信息")
    private String rightLatitude;

    /**
     * 左经度（西南角）
     */
    @NotNull(message = "左经度（西南角）不能为空")
    @NotBlank(message = "左经度（西南角）不能为空")
    @Pattern(regexp = RegexpUtils.LON_REGEXP, message = "请填写正确的经度信息")
    private String leftLongitude;

    /**
     * 左纬度（西南角）
     */
    @NotNull(message = "左纬度（西南角）不能为空")
    @NotBlank(message = "左纬度（西南角）不能为空")
    @Pattern(regexp = RegexpUtils.LAT_REGEXP, message = "请填写正确的纬度信息")
    private String leftLatitude;

    public String getRightLongitude() {
        return rightLongitude;
    }

    public void setRightLongitude(String rightLongitude) {
        this.rightLongitude = rightLongitude;
    }

    public String getRightLatitude() {
        return rightLatitude;
    }

    public void setRightLatitude(String rightLatitude) {
        this.rightLatitude = rightLatitude;
    }

    public String getLeftLongitude() {
        return leftLongitude;
    }

    public void setLeftLongitude(String leftLongitude) {
        this.leftLongitude = leftLongitude;
    }

    public String getLeftLatitude() {
        return leftLatitude;
    }

    public void setLeftLatitude(String leftLatitude) {
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
