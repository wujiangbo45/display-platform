package com.mapbar.display.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mapbar.common.UrlProperties;
import com.mapbar.common.utils.StringUtil;
import com.mapbar.common.utils.http.HttpUtil;
import com.mapbar.common.utils.http.LocalCloudRespopnse;
import com.mapbar.display.dao.WorkOrderMapper;
import com.mapbar.display.dto.SelectWorkOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wujiangbo
 * @Create: 2017/12/06 上午10:36
 */
public class WorkOrderUtil {
    @Autowired
    WorkOrderMapper workOrderMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    protected Map<String,String> totalRecord(List<SelectWorkOrderStatus> list){
        Map<String,String> m = new HashMap<>(2);
        int total = 0;
        int doingNum = 0;
        if (list != null && !list.isEmpty()){
            for (SelectWorkOrderStatus status: list) {
                total += Integer.parseInt(status.getNum().toString());
                if (1 == status.getSortType()) doingNum = status.getNum().intValue();
            }
        }
        m.put("doingNum",String.valueOf(total - doingNum));
        m.put("total",String.valueOf(total));
        return m;
    }


    protected String getUserId(){
        String userId = redisTemplate.opsForValue().get("operate_user");
        if (StringUtil.isEmpty(userId)){
            userId = HttpUtil.getLocalCloudJsonRequest(UrlProperties.getUrl("operate.getDepart"), new TypeReference<LocalCloudRespopnse<String>>() {});
            redisTemplate.opsForValue().set("operate_user",userId, 30, TimeUnit.MINUTES);
        }
        return userId;
    }
}
