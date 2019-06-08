package com.ljx.chapter17zuul;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableZuulProxy
public class Chapter17ZuulApplication implements InitializingBean {

    @Autowired
    private RedisTemplate redisTemplate ;


    @Override
    public void afterPropertiesSet() throws Exception {
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setValueSerializer(redisTemplate.getStringSerializer());
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter17ZuulApplication.class, args);
    }

}
