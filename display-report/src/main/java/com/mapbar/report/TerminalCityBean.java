package com.mapbar.report;

import org.bson.Document;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wujiangbo
 * @Create: 2018/12/18 下午3:15
 */
public class TerminalCityBean implements Serializable {

    private long tId;
    private String day;
    private long timestamp;
    private String carCph;
    private String carChassisNum;
    // 经过城市列表
    private List<DictInfo> cityList;
    // 最东西南北信息
    private TerminalLocation north;
    private TerminalLocation south;
    private TerminalLocation east;
    private TerminalLocation west;

    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    public String getCarCph() {
        return carCph;
    }


    public void setCityList(List<DictInfo> cityList) {
        this.cityList = cityList;
    }

    public TerminalLocation getNorth() {
        return north;
    }

    public void setNorth(TerminalLocation north) {
        this.north = north;
    }

    public TerminalLocation getSouth() {
        return south;
    }

    public void setSouth(TerminalLocation south) {
        this.south = south;
    }

    public TerminalLocation getEast() {
        return east;
    }

    public void setEast(TerminalLocation east) {
        this.east = east;
    }

    public TerminalLocation getWest() {
        return west;
    }

    public void setWest(TerminalLocation west) {
        this.west = west;
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

    public List<DictInfo> getCityList() {
        return cityList;
    }

    public Document toDocument(){
        return new Document()
                .append("tId", this.tId)
                .append("day", this.day)
                .append("timestamp", this.timestamp)
                .append("carChassisNum", this.carChassisNum)
                .append("carCph", this.carCph)
                .append("cityList", this.cityList.stream().map(DictInfo::toDocument).collect(Collectors.toList()))
                .append("north", this.north.toDocument())
                .append("south", this.south.toDocument())
                .append("east", this.east.toDocument())
                .append("west", this.west.toDocument());
    }

}
