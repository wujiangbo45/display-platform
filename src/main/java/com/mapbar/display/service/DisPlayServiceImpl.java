package com.mapbar.display.service;

import com.mapbar.display.dto.LocalCloudRespopnse;
import com.mapbar.display.dto.LocationDataResp;
import com.mapbar.display.dto.VehicleRealtimePositionReq;
import com.mapbar.display.dto.VehicleRealtimePositionResp;
import com.mapbar.display.util.http.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:34
 */
@Service
public class DisPlayServiceImpl implements IDisplayService{

    @Override
    public VehicleRealtimePositionResp getVehicleRealtimePosition(VehicleRealtimePositionReq req) {
        String numBit = req.getNumBits();
        // 请求位置云
        List<LocationDataResp> resp = HttpUtil.getLocalCloudJsonRequest("",new TypeReference<List<LocationDataResp>>(){});

        int size = resp.size();

        // 进行聚合


        return null;
    }
}
