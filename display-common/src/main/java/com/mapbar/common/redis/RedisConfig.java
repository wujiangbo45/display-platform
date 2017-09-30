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
//
//    @Autowired
//    private RedisProperties redisProperties;
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName(redisProperties.getHost());
//        jedisConnectionFactory.setPort(redisProperties.getPort());
//        jedisConnectionFactory.setPassword(redisProperties.getPassword());
//        jedisConnectionFactory.setDatabase(redisProperties.getDatabase());
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
//        jedisPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
//        jedisPoolConfig.setMaxTotal(redisProperties.getPool().getMaxActive());
//        jedisPoolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
//        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String,String> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        RedisTemplate<String,String> template = new RedisTemplate<String,String>();
//        template.setConnectionFactory(jedisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        return template;
//    }
////
////    @Bean
////    public JedisPool jedis(){
////        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
////        genericObjectPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
////        genericObjectPoolConfig.setMaxTotal(redisProperties.getPool().getMaxTotal());
////        genericObjectPoolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
////
////        JedisPool jedisPool = new JedisPool(genericObjectPoolConfig,redisProperties.getHost(),redisProperties.getPort());
////        return jedisPool;
////    }


}
