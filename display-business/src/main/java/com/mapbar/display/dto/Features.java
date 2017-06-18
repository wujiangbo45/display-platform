package com.mapbar.display.dto;


import com.mapbar.common.Const;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/19 13:15
 */
public class Features {

    private String type = Const.RESP_FEATURE_TYPE;

    private Geometry geometry;

    private Map<String,Integer> properties = new HashMap<String,Integer>(1);

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Map<String, Integer> getProperties() {
        return properties;
    }

    public void setProperties(Integer number) {
        this.properties.put("number",number);
    }

    @Override
    public String toString() {
        return "Features{" +
                "type='" + type + '\'' +
                ", geometry=" + geometry +
                ", properties=" + properties +
                '}';
    }
}
