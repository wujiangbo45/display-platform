package com.mapbar.display.service;

import com.mapbar.display.command.ServerStationReq;
import com.mapbar.display.common.UrlProperties;
import com.mapbar.display.dto.*;
import com.mapbar.display.result.PolymerizeResult;
import com.mapbar.display.service.base.BaseService;
import com.mapbar.display.util.*;
import com.mapbar.display.util.http.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.display.util.polymerize.PolymerizeService;
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
    public VehicleRealtimePositionResp getVehicleRealtimePosition(VehicleRealtimePositionReq req) throws Exception{
        String numBit = req.getNumBits();
        // 请求位置云
        List<LocationDataResp> resp = HttpUtil.getLocalCloudJsonRequest(UrlProperties.getUrl("localcloud.getLocationData"),new TypeReference<LocalCloudRespopnse<List<LocationDataResp>>>(){});
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

    @Override
    public List<GetServiceStatisticsResp> getServiceStatistics(GetServiceStatisticsReq req) throws Exception{
        // 拼接url
        String getUrl = HttpUtil.getUrl(UrlProperties.getUrl("localcloud.serverStations"), req);
        DistrictResp cloudRespBody = HttpUtil.getLocalCloudJsonRequest(getUrl, new TypeReference<LocalCloudRespopnse<DistrictResp>>() {
        });
        List<DistrictNumberResp> cloudResp = cloudRespBody.getDistrictList();
        int size = cloudResp.size();
        List<GetServiceStatisticsResp> resp = new ArrayList<GetServiceStatisticsResp>(size);

        for (DistrictNumberResp dictNumberObj : cloudResp) {
            GetServiceStatisticsResp ssRes = new GetServiceStatisticsResp();
            // 判断服务站是一级或者二级
            String serverStationId = String.valueOf(dictNumberObj.getDistrict());
            String sqlStr;

            if (serverStationId.startsWith("95")){// 二级服务站
                serverStationId = getStationId(serverStationId);
                sqlStr = "getSecondServiceStationInfo";
            } else{// 一级服务站
                sqlStr = "getFirstServiceStationInfo";
            }
            ServerStationReq command = new ServerStationReq();
            command.setId(serverStationId);
            ServerStationInfo info = (ServerStationInfo)dao.sqlFindObject(sqlStr,command,ServerStationInfo.class);
            ssRes.setDistrict(String.valueOf(dictNumberObj.getDistrict()));
            ssRes.setNumber(String.valueOf(dictNumberObj.getNumber()));
            if (null != info){
                ssRes.setDistrictName(info.getStaName());
            }
            resp.add(ssRes);
        }
        return resp;

    }

    /**
     * 获取二级服务站id
     * @param secondStationId
     * @return
     */
    private String getStationId(String secondStationId){
        String stationId = secondStationId.substring(2,9);
        return String.valueOf(Integer.parseInt(stationId));
    }

}
