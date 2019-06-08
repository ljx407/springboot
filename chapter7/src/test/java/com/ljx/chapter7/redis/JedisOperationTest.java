package com.ljx.chapter7.redis;

import com.ljx.chapter7.pubsub.SubscriberTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JedisOperationTest {

    private final String LISTKEY = "nameList";
    private final String SETKEY = "nameSet";
    private final String ZSETKEY = "nameZSet";
    private final String MAPKEY = "nameMap";

    private Jedis jedis ;

    @Before
    public void before() {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");

        if(jedis.exists(LISTKEY)){
            jedis.del(LISTKEY);
        }

        if(jedis.exists(SETKEY)) {
            jedis.del(SETKEY);
        }

        if(jedis.exists(ZSETKEY)) {
            jedis.del(ZSETKEY);
        }

        if(jedis.exists(MAPKEY)) {
            jedis.del(MAPKEY);
        }
    }

    @After
    public void afterMethod() {
        try {
            jedis.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testKeyValue() {
        jedis.set("table","user");
        String table = jedis.get("table");
        Assert.assertEquals(table,"user");
    }

    @Test
    public void testList() {
        jedis.rpush(LISTKEY,"jasonliu","peggy","mumu");
        long nameList = jedis.llen(LISTKEY);
        Assert.assertEquals(nameList,3L);
        String nameList1 = jedis.lindex(LISTKEY, 0);
        Assert.assertEquals(nameList1,"jasonliu");
        long jasonliu = jedis.lrem(LISTKEY, 1, "jasonliu");
        Assert.assertEquals(jasonliu,1L);
        Assert.assertEquals((long)jedis.llen(LISTKEY),2L);
    }

    @Test
    public void testSet() {
        jedis.sadd(SETKEY,"jasonliu","peggy","mumu");
        Assert.assertEquals((long)jedis.scard(SETKEY),3L);
        Assert.assertEquals((long)jedis.srem(SETKEY,"jasonliu"),1);
        ScanResult<String> sscan = jedis.sscan(SETKEY, "0");
        do {
            List<String> result = sscan.getResult();
            System.out.println(result);
        } while ((!sscan.isCompleteIteration()) ) ;
    }

    @Test
    public void testZSet() {
        for (int i = 0; i < 10; i++) {
            jedis.zadd(ZSETKEY, i , "zset"+i);

        }

        Assert.assertEquals((long)jedis.zcard(ZSETKEY),10L);
        Assert.assertEquals(jedis.zrange(ZSETKEY,0,0).stream().findFirst().orElse(null),"zset0");
    }

    @Test
    public void testHashmap() {
        for (int i = 0; i < 10; i++) {
            String key = MAPKEY + ":row" + i ;
            Map<String,String> userRow1 = new HashMap<>();
            userRow1.put("oid", String.valueOf(i));
            userRow1.put("name", "jasonliu");
            userRow1.put("age","18");
            jedis.hmset(key,userRow1);
        }

        Set<String> keys = jedis.keys(MAPKEY + ":row*");
        for (String key : keys) {
            Map<String, String> stringStringMap = jedis.hgetAll(key);
            stringStringMap.forEach((k,v) ->
                System.out.println(String.format("%s:{%s:%s}",key,k,v))
            );
        }

        Assert.assertEquals(jedis.hget(MAPKEY + ":row0", "name"),"jasonliu");
    }

    @Test
    public void testPubSub() {
        try {
            SubscriberTask subscriberTask = new SubscriberTask(jedis, "customBook");
            ExecutorService executors = Executors.newSingleThreadExecutor();
            executors.execute(subscriberTask);
            executors.awaitTermination(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

}
