package com.mapbar.display.dto;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 15:05
 */
public class GetServiceStatisticsReq {

    /**
     *开始日期
     */
    private String beginDate;
    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 地区代码
     */
    private int district;

    /**
     * 级别
     */
    private int districtType;

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

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getDistrictType() {
        return districtType;
    }

    public void setDistrictType(int districtType) {
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
