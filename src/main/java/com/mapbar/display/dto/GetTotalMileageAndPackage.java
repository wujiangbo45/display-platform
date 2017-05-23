package com.mapbar.display.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mapbar.display.util.JsonUtils;

/**
 * Created by wangjc on 2017/5/22.
 * ��ȡ����������ݰ�����
 */
public class GetTotalMileageAndPackage {
    private long mileage;
    private long packageNum;
    private int onlineNum;
    private int driveNum;


    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public long getPackageNum() {
        return packageNum;
    }

//    @JsonProperty("package")
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

    @Override
    public String toString() {
        return "GetTotalMileageAndPackage{" +
                "mileage=" + mileage +
                ", packageNum=" + packageNum +
                ", onlineNum=" + onlineNum +
                ", driveNum=" + driveNum +
                '}';
    }
}
