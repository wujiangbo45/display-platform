package com.mapbar.report;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2018/12/20 下午4:39
 */
public class ReportTable implements Serializable {
    private long tId;
    private String carCph;
    private String carChassisNum;
    // 经过城市列表
    private List<DictInfo> cityList;


    // 最东西南北信息
    private String north;
    private String south;
    private String east;
    private String west;
    private double mMilage;
    /**
     * 当日油耗量
     */
    private double fuel;
    /**
     * 当日工作时长 秒
     */
    private double workHours;

    private double avgFuel;

    private long joinTime;


    public List<DictInfo> getCityList() {
        return cityList;
    }

    public void setCityList(List<DictInfo> cityList) {
        this.cityList = cityList;
    }

    public long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(long joinTime) {
        this.joinTime = joinTime;
    }

    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
    }

    public String getCarCph() {
        return carCph;
    }

    public void setCarCph(String carCph) {
        this.carCph = carCph;
    }

    public String getCarChassisNum() {
        return carChassisNum;
    }

    public void setCarChassisNum(String carChassisNum) {
        this.carChassisNum = carChassisNum;
    }



    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public double getmMilage() {
        return mMilage;
    }

    public void setmMilage(double mMilage) {
        this.mMilage = mMilage;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public double getAvgFuel() {
        return avgFuel;
    }

    public void setAvgFuel(double avgFuel) {
        this.avgFuel = avgFuel;
    }
}
