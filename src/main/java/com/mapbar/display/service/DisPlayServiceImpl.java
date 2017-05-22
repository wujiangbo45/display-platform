package com.mapbar.display.service;

import com.mapbar.display.dto.*;
import com.mapbar.display.service.base.BaseService;
import com.mapbar.display.util.*;
import com.mapbar.display.util.http.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:34
 */
@Service
public class DisPlayServiceImpl extends BaseService implements IDisplayService{


    @Autowired
    PolymerizeService polymerizeService;

    @Override
    public VehicleRealtimePositionResp getVehicleRealtimePosition(VehicleRealtimePositionReq req) {
        String numBit = req.getNumBits();
        // 请求位置云
        List<LocationDataResp> resp = HttpUtil.getLocalCloudJsonRequest("",new TypeReference<List<LocationDataResp>>(){});
        int size = resp.size();
        List<PolymerizeDto> dtoList = new ArrayList<>(size);
        for (LocationDataResp data : resp){
            PolymerizeDto dto = new PolymerizeDto();
            dto.setLat(data.getLat());
            dto.setLon(data.getLng());
            dto.setPropertyInfo("1");
            dtoList.add(dto);
        }
        // 进行聚合
        List<PolymerizeResult> result = polymerizeService.getPolymerizeResult(dtoList,Integer.parseInt(numBit));
        return CoordinateInfoUtil.castPolymerizeToResp(result);
    }



}
