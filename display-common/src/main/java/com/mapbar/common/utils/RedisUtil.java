package com.mapbar.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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

    /**
     * 清除单个key
     * @param key
     */
    public void delete(String key){
        redisTemplate.delete(key);
    }


    /**
     * 批量删除key
     * @param key
     */
    public void delete(String ... key){
        redisTemplate.delete(Arrays.asList(key));
    }


    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

}
