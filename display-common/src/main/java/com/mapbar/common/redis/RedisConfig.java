package com.mapbar.common.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zhangdong on 2017/5/19.
 */
//@Configuration
//@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

//   没有啊
//
//    @Bean
//    public JedisPool jedis(){
//        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
//        genericObjectPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
//        genericObjectPoolConfig.setMaxTotal(redisProperties.getPool().getMaxTotal());
//        genericObjectPoolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
//
//        JedisPool jedisPool = new JedisPool(genericObjectPoolConfig,redisProperties.getHost(),redisProperties.getPort());
//        return jedisPool;
//    }


}
