package com.ljx.chapter7.pubsub;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPubSub;

@Slf4j
public class CustomSubscriber extends JedisPubSub {

    public CustomSubscriber() {
    }

    public void onMessage(String channel, String message) {
        log.info("CustomSubscriber#onMessage begin :");
        log.info("channel:{} , message:{}" , channel, message);
        log.info("CustomSubscriber#onMessage end");
    }

    public void onSubscribe(String channel, int subscribedChannels) {
        log.info("CustomSubscriber#onSubscribe begin :");
        log.info("channel:{} , message:{}" , channel, subscribedChannels);
        log.info("CustomSubscriber#onSubscribe end");


    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
        log.info("CustomSubscriber#onUnsubscribe begin :");
        log.info("channel:{} , message:{}" , channel, subscribedChannels);
        log.info("CustomSubscriber#onUnsubscribe end");

    }


}
