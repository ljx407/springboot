package com.ljx.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter12ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
//        Boolean aBoolean = redisTemplate.hasKey("user:jasonliu");
//        log.info(aBoolean.toString());
//
//        Jedis jedis = new Jedis("localhost",6379);
//        jedis.auth("123456");
//        Set<String> keys = jedis.keys("*");
//        log.info(keys.toString());

//        log.info(passwordEncoder.encode("123"));
//        redisTemplate.opsForValue().set("user:jasonliu", passwordEncoder.encode("123"));
//        redisTemplate.opsForList().leftPush("role:jasonliu","admin");
//


    }

}
