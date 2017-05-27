package com.mapbar.display.polymerize;


import com.mapbar.display.dto.Features;
import com.mapbar.display.dto.Geometry;
import com.mapbar.display.dto.VehicleRealtimePositionResp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjc on 2017/5/19.
 */
public class CoordinateInfoUtil {
    public static VehicleRealtimePositionResp castPolymerizeToResp(List<PolymerizeResp> coorList){

        VehicleRealtimePositionResp resp = new VehicleRealtimePositionResp();
        List<Features> f = new ArrayList<>();
        for (PolymerizeResp pMr : coorList) {
            Features d = new Features();
            Geometry g = new Geometry();
            g.setCoordinates(pMr.getLon(), pMr.getLat());
            d.setGeometry(g);
            if(pMr.getCarCount() > 1 ){
                d.setProperties(String.valueOf(pMr.getCarCount()));
            }
            f.add(d);
        }
        resp.setFeatures(f);
        return resp;
    }
}
