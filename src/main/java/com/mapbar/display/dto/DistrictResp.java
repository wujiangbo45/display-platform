package com.mapbar.display.dto;

import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 17:22
 */
public class DistrictResp {

    private Integer type;

    private List<DistrictNumberResp> districtList;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<DistrictNumberResp> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<DistrictNumberResp> districtList) {
        this.districtList = districtList;
    }
}
