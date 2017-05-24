package com.mapbar.display.dto;

import com.mapbar.display.util.RegexpUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 15:05
 */
public class GetServiceStatisticsReq {

    /**
     *开始日期
     */
    @NotNull(message = "开始日期不能为空")
    @NotBlank(message = "开始日期不能为空")
    @Pattern(regexp = RegexpUtils.DATE_YYYY_MM_DD_REGEXP, message = "开始日期格式不正确")
    private String beginDate;
    /**
     * 结束日期
     */
    @NotNull(message = "结束日期不能为空")
    @NotBlank(message = "结束日期不能为空")
    @Pattern(regexp = RegexpUtils.DATE_YYYY_MM_DD_REGEXP, message = "结束日期格式不正确")
    private String endDate;

    /**
     * 地区代码
     */
    @NotNull(message = "地区代码不能为空")
    @NotBlank(message = "地区代码不能为空")
    private String district;

    /**
     * 级别
     */
    @NotNull(message = "级别不能为空")
    @NotBlank(message = "级别不能为空")
    @Pattern(message = "级别只能为0,1,2",regexp = "^[0-2]")
    private String districtType;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictType() {
        return districtType;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    @Override
    public String toString() {
        return "GetServiceStatisticsReq{" +
                "beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", district=" + district +
                ", districtType=" + districtType +
                '}';
    }
}
