package com.mapbar.display.dto;

import com.mapbar.common.Const;

import java.util.List;


/**
 * @Author: wujiangbo
 * @Create: 2017/05/19 13:10
 */
public class VehicleRealtimePositionResp {

    private String type = Const.RESP_REALTIME_POSITION_TYPE;

    private List<Features> features;

    public String getType() {
        return type;
    }

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "VehicleRealtimePositionResp{" +
                "type='" + type + '\'' +
                ", features=" + features +
                '}';
    }
}
