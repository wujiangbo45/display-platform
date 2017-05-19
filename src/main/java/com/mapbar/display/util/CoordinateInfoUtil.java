package com.mapbar.display.util;

import com.mapbar.display.dto.Features;
import com.mapbar.display.dto.Geometry;
import com.mapbar.display.dto.VehicleRealtimePositionResp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjc on 2017/5/19.
 * 聚合点车辆坐标和数量的返回
 */
public class CoordinateInfoUtil {
    public List<VehicleRealtimePositionResp> getCarInfo(List<PolymerizeResult> coorList){

        List<VehicleRealtimePositionResp> list = new ArrayList<VehicleRealtimePositionResp>();
        for(int i = 0;i < coorList.size();i ++){
            VehicleRealtimePositionResp resp = new VehicleRealtimePositionResp();
            List<Features> f = new ArrayList<>();
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
            resp.setFeatures(f);
            list.add(resp);

        }
        return list;
    }
}
