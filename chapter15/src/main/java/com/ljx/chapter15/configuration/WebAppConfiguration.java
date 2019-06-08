package com.ljx.chapter15.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class WebAppConfiguration implements InitializingBean {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setValueSerializer(redisTemplate.getStringSerializer());
    }




}
