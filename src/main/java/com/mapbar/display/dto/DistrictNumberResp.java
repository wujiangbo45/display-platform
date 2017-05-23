package com.mapbar.display.dto;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 15:08
 */
public class DistrictNumberResp {

    /**
     * 区域编码/服务站标识
     */
    private int district;

    /**
     * 区域编码/服务站标识 对应数值
     */
    private int number;

    /**
     * 类型：0 省 ；1 服务站
     */
    private int type;

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DistrictNumberResp{" +
                "district=" + district +
                ", number=" + number +
                ", type=" + type +
                '}';
    }
}
