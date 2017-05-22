package com.mapbar.display.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjie
 * @version 1.0
 * @date 2016-10-11
 * @desc 封装redisTemplate
 * @modify
 */
@Component
public class RedisUtil
{
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static String redisCode = "utf-8";


    /**
     *  获取值
     * @param key 键
     * @return String
     */
    public String get(final String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     *@param key 键
     *@param liveTime 有效期 毫秒
     * 设置有效期
     * */
    public void set(final String key, final String value, final Long liveTime){
        redisTemplate.boundValueOps(key).set(value, liveTime, TimeUnit.MINUTES);
    }

    public void set(final String key, final String value){
        redisTemplate.boundValueOps(key).set(value);
    }

    public void expire(final String key, final Long liveTime){
        redisTemplate.expire(key,liveTime,TimeUnit.MINUTES);
    }

    /**
     * 根据key进行自增
     * @author wujianbo
     * @param key
     * @param increment
     * @return
     */
    public Long incr(String key,Long increment){
        return redisTemplate.opsForValue().increment(key,increment);
    }
}
