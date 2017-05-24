package com.mapbar.common.utils;

import com.mapbar.display.dto.Features;
import com.mapbar.display.dto.Geometry;
import com.mapbar.display.dto.VehicleRealtimePositionResp;
import com.mapbar.common.utils.polymerize.PolymerizeResp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjc on 2017/5/19.
 */
public class CoordinateInfoUtil {
    public static VehicleRealtimePositionResp castPolymerizeToResp(List<PolymerizeResp> coorList){

        VehicleRealtimePositionResp resp = new VehicleRealtimePositionResp();
        List<Features> f = new ArrayList<>();
        for(int i = 0;i < coorList.size();i ++){
            Features d = new Features();
            Geometry g = new Geometry();
            g.setCoordinates(coorList.get(i).getLat(), coorList.get(i).getLon());
            d.setGeometry(g);
            if(coorList.get(i).getCarCount() != 0 && coorList.get(i).getCarCount() != null){
                d.setProperties(coorList.get(i).getCarCount().toString());
            }else{
                d.setProperties("0");
            }
            f.add(d);
        }
        resp.setFeatures(f);
        return resp;
    }
}
