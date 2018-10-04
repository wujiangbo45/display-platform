package com.mapbar.display.dto;

import java.math.BigDecimal;

/**
 * @Author: wujiangbo
 * @Create: 2017/09/30 上午10:54
 */
public class WorkOrderGroupByStatus {

    private BigDecimal individualNum; //待分派数量

    private BigDecimal waitingCarNum; //待接车数量

    private BigDecimal checkInNum; //检查中数量

    private BigDecimal repairNum; //维修中数量

    private BigDecimal unConfirmedNum; //完成待确认数量

    private BigDecimal waitingStationNum; //待出站数量

    private BigDecimal exitStationNum; //已出站数量

    private BigDecimal systemClose; //系统关闭数量

    private BigDecimal num; //全部工单

    public BigDecimal getIndividualNum() {
        return individualNum;
    }

    public void setIndividualNum(BigDecimal individualNum) {
        this.individualNum = individualNum;
    }

    public BigDecimal getWaitingCarNum() {
        return waitingCarNum;
    }

    public void setWaitingCarNum(BigDecimal waitingCarNum) {
        this.waitingCarNum = waitingCarNum;
    }

    public BigDecimal getCheckInNum() {
        return checkInNum;
    }

    public void setCheckInNum(BigDecimal checkInNum) {
        this.checkInNum = checkInNum;
    }

    public BigDecimal getRepairNum() {
        return repairNum;
    }

    public void setRepairNum(BigDecimal repairNum) {
        this.repairNum = repairNum;
    }

    public BigDecimal getUnConfirmedNum() {
        return unConfirmedNum;
    }

    public void setUnConfirmedNum(BigDecimal unConfirmedNum) {
        this.unConfirmedNum = unConfirmedNum;
    }

    public BigDecimal getWaitingStationNum() {
        return waitingStationNum;
    }

    public void setWaitingStationNum(BigDecimal waitingStationNum) {
        this.waitingStationNum = waitingStationNum;
    }

    public BigDecimal getExitStationNum() {
        return exitStationNum;
    }

    public void setExitStationNum(BigDecimal exitStationNum) {
        this.exitStationNum = exitStationNum;
    }

    public BigDecimal getSystemClose() {
        return systemClose;
    }

    public void setSystemClose(BigDecimal systemClose) {
        this.systemClose = systemClose;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "WorkOrderGroupByStatus{" +
                "individualNum=" + individualNum +
                ", waitingCarNum=" + waitingCarNum +
                ", checkInNum=" + checkInNum +
                ", repairNum=" + repairNum +
                ", unConfirmedNum=" + unConfirmedNum +
                ", waitingStationNum=" + waitingStationNum +
                ", exitStationNum=" + exitStationNum +
                ", systemClose=" + systemClose +
                ", num=" + num +
                '}';
    }
}
