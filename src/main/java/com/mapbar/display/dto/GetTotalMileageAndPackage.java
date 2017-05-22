package com.mapbar.display.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mapbar.display.util.JsonUtils;

/**
 * Created by wangjc on 2017/5/22.
 * 获取总里程总数据包条数
 */
public class GetTotalMileageAndPackage {
    private int resultCode;
    private long mileage;
    private long packageNum;
    private int onlineNum;
    private int driveNum;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public long getPackageNum() {
        return packageNum;
    }

    @JsonProperty("package")
    public void setPackageNum(long packageNum) {
        this.packageNum = packageNum;
    }

    public int getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(int onlineNum) {
        this.onlineNum = onlineNum;
    }

    public int getDriveNum() {
        return driveNum;
    }

    public void setDriveNum(int driveNum) {
        this.driveNum = driveNum;
    }

}
