package com.mapbar.display.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.common.Const;
import com.mapbar.common.UrlProperties;
import com.mapbar.common.utils.JsonUtils;
import com.mapbar.common.utils.http.HttpUtil;
import com.mapbar.common.utils.http.LocalCloudRespopnse;
import com.mapbar.display.dto.LocationDataResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/27 11:39
 */
@Component
public class ScheduledService{
    private static final Logger logger = LoggerFactory.getLogger(ScheduledService.class);

//    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Scheduled(cron="0 0/3 * * * ?")
    public void executeFileDownLoadTask() {
        List<LocationDataResp> resp = HttpUtil.getLocalCloudJsonRequest(UrlProperties.getUrl("localcloud.getLocationData"),new TypeReference<LocalCloudRespopnse<List<LocationDataResp>>>(){});
        // 存储缓存
        redisTemplate.boundValueOps(Const.LOCATION_DATA_KEY).set(JsonUtils.toJson(resp));
    }
}
