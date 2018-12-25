package com.mapbar.report;

import org.bson.Document;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2017-04-24
 * @modify
 */

public class CountEntity implements Serializable {

    private long terminalId;
    /**
     * 数据日期
     */
    private String date;

    private long timestamp;

    /**
     * 速度值的累加值
     */
    private long speedSum;
    /**
     * 速度个数
     */
    private int speedNum;

    /**
     * 当日仪表里程
     */
    private double mMilage;
    /**
     * 当日GPS里程
     */
    private double mGps;
    /**
     * 当日油耗量
     */
    private double fuel;
    /**
     * 当日工作时长 秒
     */
    private double workHours;
    /**
     * 当日怠速时长 秒
     */
    private double idleHours;
    /**
     * 急刹车次数
     */
    private int stboTimes;
    /**
     * 急加速次数
     */
    private int acceTimes;
    /**
     * 超速次数
     */
    private int overspeedTimes;
    /**
     * 空挡滑行次数
     */
    private int hsnsTimes;
    /**
     * 疲劳驾驶次数
     */
    private int tdTimes;
    /**
     * 日百公里油耗
     */
    private double fuelRateDay;

    /**
     * 平均车速(不含怠速)
     */
    private double avgSpeedNoidle;
    /**
     * 平均车速(含怠速)
     */
    private double avgSpeed;
    /**
     * 怠速占比
     */
    private double idleP;
    /**
     * 日均空挡滑行
     */
    private double avgHsnsDay;
    /**
     * 超转速
     */
    private int hesTimes;
    /**
     * 经济区占比
     */
    private double ofeP;
    /**
     * 刹车次数
     */
    private int brakeTimes;
    /**
     * 超速区占比
     */
    private double overspeedP;
    /**
     * 油量消耗
     */
    private double oilValue;
    /**
     * 开始里程
     */
    private double bMil;
    /**
     * 结束里程
     */
    private double eMil;
    /**
     * 开始经纬度
     */
    private int bLat;
    /**
     * 开始经纬度
     */
    private int bLng;
    /**
     * 结束经纬度
     */
    private int eLat;
    /**
     * 结束经纬度
     */
    private int eLng;
    /**
     * 开始时间
     */
    private int start;
    /**
     * 结束时间
     */
    private int end;
    /**
     * 运行时长
     */
    private int sDate; //统计运行时长


    public long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(long terminalId) {
        this.terminalId = terminalId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getSpeedSum() {
        return speedSum;
    }

    public void setSpeedSum(long speedSum) {
        this.speedSum = speedSum;
    }

    public int getSpeedNum() {
        return speedNum;
    }

    public void setSpeedNum(int speedNum) {
        this.speedNum = speedNum;
    }

    public double getmMilage() {
        return mMilage;
    }

    public void setmMilage(double mMilage) {
        this.mMilage = mMilage;
    }

    public double getmGps() {
        return mGps;
    }

    public void setmGps(double mGps) {
        this.mGps = mGps;
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

    public double getIdleHours() {
        return idleHours;
    }

    public void setIdleHours(double idleHours) {
        this.idleHours = idleHours;
    }

    public int getStboTimes() {
        return stboTimes;
    }

    public void setStboTimes(int stboTimes) {
        this.stboTimes = stboTimes;
    }

    public int getAcceTimes() {
        return acceTimes;
    }

    public void setAcceTimes(int acceTimes) {
        this.acceTimes = acceTimes;
    }

    public int getOverspeedTimes() {
        return overspeedTimes;
    }

    public void setOverspeedTimes(int overspeedTimes) {
        this.overspeedTimes = overspeedTimes;
    }

    public int getHsnsTimes() {
        return hsnsTimes;
    }

    public void setHsnsTimes(int hsnsTimes) {
        this.hsnsTimes = hsnsTimes;
    }

    public int getTdTimes() {
        return tdTimes;
    }

    public void setTdTimes(int tdTimes) {
        this.tdTimes = tdTimes;
    }

    public double getFuelRateDay() {
        return fuelRateDay;
    }

    public void setFuelRateDay(double fuelRateDay) {
        this.fuelRateDay = fuelRateDay;
    }

    public double getAvgSpeedNoidle() {
        return avgSpeedNoidle;
    }

    public void setAvgSpeedNoidle(double avgSpeedNoidle) {
        this.avgSpeedNoidle = avgSpeedNoidle;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public double getIdleP() {
        return idleP;
    }

    public void setIdleP(double idleP) {
        this.idleP = idleP;
    }

    public double getAvgHsnsDay() {
        return avgHsnsDay;
    }

    public void setAvgHsnsDay(double avgHsnsDay) {
        this.avgHsnsDay = avgHsnsDay;
    }

    public int getHesTimes() {
        return hesTimes;
    }

    public void setHesTimes(int hesTimes) {
        this.hesTimes = hesTimes;
    }

    public double getOfeP() {
        return ofeP;
    }

    public void setOfeP(double ofeP) {
        this.ofeP = ofeP;
    }

    public int getBrakeTimes() {
        return brakeTimes;
    }

    public void setBrakeTimes(int brakeTimes) {
        this.brakeTimes = brakeTimes;
    }

    public double getOverspeedP() {
        return overspeedP;
    }

    public void setOverspeedP(double overspeedP) {
        this.overspeedP = overspeedP;
    }

    public double getOilValue() {
        return oilValue;
    }

    public void setOilValue(double oilValue) {
        this.oilValue = oilValue;
    }

    public double getbMil() {
        return bMil;
    }

    public void setbMil(double bMil) {
        this.bMil = bMil;
    }

    public double geteMil() {
        return eMil;
    }

    public void seteMil(double eMil) {
        this.eMil = eMil;
    }

    public int getbLat() {
        return bLat;
    }

    public void setbLat(int bLat) {
        this.bLat = bLat;
    }

    public int getbLng() {
        return bLng;
    }

    public void setbLng(int bLng) {
        this.bLng = bLng;
    }

    public int geteLat() {
        return eLat;
    }

    public void seteLat(int eLat) {
        this.eLat = eLat;
    }

    public int geteLng() {
        return eLng;
    }

    public void seteLng(int eLng) {
        this.eLng = eLng;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getsDate() {
        return sDate;
    }

    public void setsDate(int sDate) {
        this.sDate = sDate;
    }


    public Document toDocument() {
        return new Document()
        .append("terminalId",this.terminalId)
        .append("date",this.date)
        .append("timestamp",this.timestamp)
        .append("speedSum",this.speedSum)
        .append("speedNum",this.speedNum)
        .append("mMilage",this.mMilage)
        .append("mGps",this.mGps)
        .append("fuel",this.fuel)
        .append("workHours",this.workHours)
        .append("idleHours",this.idleHours)
        .append("stboTimes",this.stboTimes)
        .append("acceTimes",this.acceTimes)
        .append("overspeedTimes",this.overspeedTimes)
        .append("hsnsTimes",this.hsnsTimes)
        .append("tdTimes",this.tdTimes)
        .append("fuelRateDay",this.fuelRateDay)
        .append("avgSpeedNoidle",this.avgSpeedNoidle)
        .append("avgSpeed",this.avgSpeed)
        .append("idleP",this.idleP)
        .append("avgHsnsDay",this.avgHsnsDay)
        .append("hesTimes",this.hesTimes)
        .append("ofeP",this.ofeP)
        .append("brakeTimes",this.brakeTimes)
        .append("overspeedP",this.overspeedP)
        .append("oilValue",this.oilValue)
        .append("bMil",this.bMil)
        .append("eMil",this.eMil)
        .append("bLat",this.bLat)
        .append("bLng",this.bLng)
        .append("eLat",this.eLat)
        .append("eLng",this.eLng)
        .append("start",this.start)
        .append("end",this.end)
        .append("sDate",this.sDate)
        ;
    }
}
