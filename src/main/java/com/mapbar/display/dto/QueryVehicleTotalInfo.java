package com.mapbar.display.dto;

/**
 * Created by wangjc on 2017/5/22.
 *
 */
public class QueryVehicleTotalInfo {
    private String cumulativeTotalMileage;
    private String totalVehicle;
    private String onlineVehicle;
    private String runningVehicle;
    private String uploadDataCount;

    public String getTotalVehicle() {
        return totalVehicle;
    }

    public void setTotalVehicle(String totalVehicle) {
        this.totalVehicle = totalVehicle;
    }

    public String getOnlineVehicle() {
        return onlineVehicle;
    }

    public void setOnlineVehicle(String onlineVehicle) {
        this.onlineVehicle = onlineVehicle;
    }

    public String getRunningVehicle() {
        return runningVehicle;
    }

    public void setRunningVehicle(String runningVehicle) {
        this.runningVehicle = runningVehicle;
    }

    public String getUploadDataCount() {
        return uploadDataCount;
    }

    public void setUploadDataCount(String uploadDataCount) {
        this.uploadDataCount = uploadDataCount;
    }

    public String getCumulativeTotalMileage() {
        return cumulativeTotalMileage;
    }

    public void setCumulativeTotalMileage(String cumulativeTotalMileage) {
        this.cumulativeTotalMileage = cumulativeTotalMileage;
    }
}
