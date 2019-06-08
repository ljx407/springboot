package com.ljx.chapter7.pubsub;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class SubscriberTask implements Runnable {
    private CustomSubscriber customSubscriber = new CustomSubscriber() ;
    private String channel ;
    private Jedis jedis ;

    public SubscriberTask(Jedis jedis,String channel) {
        this.channel = channel;
        this.jedis = jedis ;
    }

    @Override
    public void run() {
        log.info("####SubscriberTask run begin :");
        jedis.subscribe(customSubscriber, channel);

    }
}
