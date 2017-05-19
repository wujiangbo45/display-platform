package com.mapbar.display.dto;

import java.util.List;

import static com.mapbar.display.common.Const.RESP_REALTIME_POSITION_TYPE;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/19 13:10
 */
public class VehicleRealtimePositionResp {

    private String type = RESP_REALTIME_POSITION_TYPE;

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
}
