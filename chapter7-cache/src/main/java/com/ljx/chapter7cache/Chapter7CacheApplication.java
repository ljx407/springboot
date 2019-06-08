package com.ljx.chapter7cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import javax.annotation.PostConstruct;

@MapperScan(
        basePackages = {"com.ljx.chapter7cache.dao"}
        )
@EnableCaching
@SpringBootApplication
public class Chapter7CacheApplication {

    @Autowired
    private RedisTemplate redisTemplate ;

    public static void main(String[] args) {

        SpringApplication.run(Chapter7CacheApplication.class, args);
    }

    @PostConstruct
    public void postConstuct() {
        initRedisTemplate(redisTemplate);
    }

    public void initRedisTemplate(RedisTemplate redisTemplate) {
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(redisTemplate.getStringSerializer());
    }

}
