package com.mapbar.display.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.common.Const;
import com.mapbar.common.UrlProperties;
import com.mapbar.common.exception.ErrorCode;
import com.mapbar.common.exception.business.TokenExpireException;
import com.mapbar.common.exception.business.UserCheckException;
import com.mapbar.common.utils.*;
import com.mapbar.common.utils.http.HttpUtil;
import com.mapbar.common.utils.http.LocalCloudRespopnse;
import com.mapbar.display.dao.HyCarMapper;
import com.mapbar.display.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.mapbar.display.polymerize.CoordinateInfoUtil;
import com.mapbar.display.polymerize.PolymerizeResp;
import com.mapbar.display.polymerize.PolymerizeService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 9:34
 */
@Service
public class DisplayServiceImpl implements IDisplayService{


    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    PolymerizeService polymerizeService;

    @Autowired
    HyCarMapper hyCarMapper;

    @Override
    public VehicleRealtimePositionResp getVehicleRealtimePosition(VehicleRealtimePositionReq req) throws Exception{
        String numBit = req.getNumBits();
        // 从缓存获取数据
        List<LocationDataResp> resp = JsonUtils.fromJson(redisTemplate.boundValueOps(Const.LOCATION_DATA_KEY).get(), new TypeReference<List<LocationDataResp>>() {
        });
        // 过滤数据
        List<PolymerizeDto> dtoList = polymerizeService.filterScreenCarToDto(resp, req);
//        int numBits = polymerizeService.setPrecision(req.getRightLatitude(),req.getRightLongitude(),req.getLeftLatitude(),req.getLeftLongitude());
        // 进行聚合
        List<PolymerizeResp> result = polymerizeService.getPolymerizeResult(dtoList,Integer.parseInt(numBit));

        return CoordinateInfoUtil.castPolymerizeToResp(result);
    }


//    public VehicleRealtimePositionResp test(VehicleRealtimePositionReq req) throws Exception{
//        String numBit = req.getNumBits();
//        List<LocationDataResp> resp = HttpUtil.getLocalCloudJsonRequest(UrlProperties.getUrl("localcloud.getLocationData"),new TypeReference<LocalCloudRespopnse<List<LocationDataResp>>>(){});
//        int size = resp.size();
//        List<PolymerizeDto> dtoList = new ArrayList<>(size);
//        for (LocationDataResp data : resp){
//            PolymerizeDto dto = new PolymerizeDto();
//            dto.setLat(data.getLat());
//            dto.setLon(data.getLng());
//            dto.setPropertyInfo("1");
//            dtoList.add(dto);
//        }
//        // 进行聚合
//        List<PolymerizeResp> result = polymerizeService.getPolymerizeResult(dtoList,Integer.parseInt(numBit));
//        return CoordinateInfoUtil.castPolymerizeToResp(result);
//    }


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
            ServerStationInfo info = null;
            if (serverStationId.startsWith("95")){// 二级服务站
                serverStationId = getStationId(serverStationId);
                info = hyCarMapper.getSecondServiceStationInfo(serverStationId);
            } else{// 一级服务站
                info = hyCarMapper.getFirstServiceStationInfo(serverStationId);
            }
            ssRes.setDistrict(String.valueOf(dictNumberObj.getDistrict()));
            ssRes.setNumber(String.valueOf(dictNumberObj.getNumber()));
            if (null != info){
                ssRes.setDistrictName(info.getStaname());
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
        HyAccountDto hyAccountDto = hyCarMapper.queryLoginInfoSql(command.getUsername());
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
                if(redisTemplate.hasKey(uKey)){
                    redisTemplate.delete(uKey);
                    redisTemplate.delete(Const.TOKEN_KEY_PREFX + redisTemplate.boundValueOps(uKey).get());
                }
                // 存入redis
                redisTemplate.boundValueOps(uKey).set(token, Const.TOKEN_LIVE_TIME_MINUTE);//用户对应的token
                redisTemplate.boundValueOps(tKey).set(json,Const.TOKEN_LIVE_TIME_MINUTE);//token对应的用户信息

            } else {
                throw new UserCheckException(ErrorCode.ERROR_PASSWD);
            }
        } else {
            throw new UserCheckException(ErrorCode.USER_NOT_FOUND);
        }

        HyAccountResp hyAccountResp = new HyAccountResp();
        hyAccountResp.setRealname(hyAccountDto.getRealname());
        hyAccountResp.setToken(hyAccountDto.getToken());
        return hyAccountResp;
    }

    @Override
    public int getTotalVehicle() throws Exception{
        return hyCarMapper.carTotal();
    }

    @Override
    public void logOut(LogoutReq req) throws Exception {
        // cha
        String token = req.getToken(); // stoken_xxx
        String cacheTokenKey = Const.TOKEN_KEY_PREFX + token;
        // 查询是否有此token
        if (!redisTemplate.hasKey(cacheTokenKey)){
            // 无此token
            throw new TokenExpireException("token:" + token);
        }
        // 获取token信息
        HyAccountDto res = JsonUtils.fromJson(redisTemplate.boundValueOps(cacheTokenKey).get(),HyAccountDto.class);
        // 获取用户名并且清空缓存 user_xxx
        String uKey = Const.USER_KEY_PREFX + res.getAccountname();
        // 清空缓存
        redisTemplate.delete(cacheTokenKey);
        redisTemplate.delete(uKey);

    }
}
