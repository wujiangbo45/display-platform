package com.mapbar.display.service;

import com.mapbar.common.Const;
import com.mapbar.common.exception.ErrorCode;
import com.mapbar.common.exception.business.UserCheckException;
import com.mapbar.display.dto.ServerStationReq;
import com.mapbar.common.UrlProperties;
import com.mapbar.display.dto.*;
import com.mapbar.common.utils.polymerize.PolymerizeResp;
import com.mapbar.common.base.BaseService;
import com.mapbar.common.utils.*;
import com.mapbar.common.utils.http.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.common.utils.polymerize.PolymerizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:34
 */
@Service
public class DisplayServiceImpl extends BaseService implements IDisplayService{


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
        List<PolymerizeResp> result = polymerizeService.getPolymerizeResult(dtoList,Integer.parseInt(numBit));
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

    @Override
    public HyAccountResp login(LoginInReq command) throws Exception{
        // 查询用户信息
        String accountPwd = "";
        HyAccountResp hyAccountDto = (HyAccountResp)dao.sqlFindObject("queryLoginInfoSql", command, HyAccountResp.class);
        if (null != hyAccountDto && command.getUsername().equals(hyAccountDto.getAccountname())) {
            accountPwd = hyAccountDto.getAccountpwd();
            // 密码md5加密校验
            MD5Util md5Util = new MD5Util();
            if (md5Util.checkPasswordAndUserPwd(command.getPassword(), accountPwd)) {
                // 生成token
                String token = StringUtil.getUUID();
                hyAccountDto.setToken(token);

                // json格式转换
                String json = JsonUtil.toJson(hyAccountDto);

                // redis对应的key
                String uKey = Const.USER_KEY_PREFX + command.getUsername();
                String tKey = Const.TOKEN_KEY_PREFX + token;
                // 删除redis记录
                if(redisUtil.hasKey(uKey)){
                    redisUtil.delete(uKey);
                    redisUtil.delete(Const.TOKEN_KEY_PREFX + redisUtil.get(uKey));
                }
                // 存入redis
                redisUtil.set(uKey, token, Const.TOKEN_LIVE_TIME_MINUTE);//用户对应的token
                redisUtil.set(tKey, json,Const.TOKEN_LIVE_TIME_MINUTE);//token对应的用户信息

            } else {
                throw new UserCheckException(ErrorCode.ERROR_PASSWD);
            }
        } else {
            throw new UserCheckException(ErrorCode.USER_NOT_FOUND);
        }
        return hyAccountDto;
    }

    @Override
    public int getTotalVehicle() throws Exception{
        int intTotal = 0;

        String countSql = sqlLaberUtil.getSqlLabel("carTotal");
        int count = Integer.parseInt(entityManager.createNativeQuery(countSql).getSingleResult().toString());
        intTotal = count;

        return intTotal;
    }

}
