package com.ljx.chapter7.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@Slf4j
public class RedisMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("#####messageBody:" + String.valueOf(message.getBody()));
        log.info("#####messageChannle" + String.valueOf(message.getChannel()));
        log.info("####pattern" + String.valueOf(pattern));
    }
}
