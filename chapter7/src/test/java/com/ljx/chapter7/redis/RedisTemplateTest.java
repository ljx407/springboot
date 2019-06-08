package com.ljx.chapter7.redis;

import com.ljx.chapter7.configuration.RedisConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.assertj.core.util.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RunWith(SpringRunner.class)
public class RedisTemplateTest {

    private static AnnotationConfigApplicationContext annotationConfigApplicationContext = null ;
    private RedisTemplate redisTemplate = null ;

    @BeforeClass
    public static void beforClass() {
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(RedisConfiguration.class);
    }

    @Before
    public void before() {
        redisTemplate = annotationConfigApplicationContext.getBean(RedisTemplate.class);


    }

    @Test
    public void testValue() {

        if(redisTemplate.hasKey("table")){
            redisTemplate.delete("table");
        }
        if(redisTemplate.hasKey("table2")){
            redisTemplate.delete("table2");
        }

        redisTemplate.opsForValue().set("table","user");
        String table = (String)redisTemplate.opsForValue().get("table");
        Assert.assertEquals(table,"user");

        redisTemplate.opsForValue().setIfPresent("table2","user2");
        Assert.assertNull(redisTemplate.opsForValue().get("table2"));

        Object table2OldValue = redisTemplate.opsForValue().get("table");
        Object replaceValue = redisTemplate.opsForValue().getAndSet("table", "22");
        Assert.assertEquals(table2OldValue,replaceValue );
        Object newValue = redisTemplate.opsForValue().get("table");
        Assert.assertEquals(newValue,"22");

    }

    @Test
    public void testList() {
        if(redisTemplate.hasKey("keyList")){
            redisTemplate.delete("keyList");
        }

        String keyList = "keyList";
        BoundListOperations boundListOperations = redisTemplate.boundListOps(keyList);
        boundListOperations.leftPushAll("1","2","3","4");

        Assert.assertEquals(boundListOperations.index(0),"4");

        long size = boundListOperations.size();
        Assert.assertEquals(size,4L);
        List<String> range = boundListOperations.range(0, -1);
        Assert.assertEquals(range.get(0),"4");

    }

    @Test
    public void testSet() {

        String setKey = "testSet";
        String setKey1 = "testSet1";
        if(redisTemplate.hasKey(setKey)) {
            redisTemplate.delete(setKey);
        }
        if(redisTemplate.hasKey(setKey1)) {
            redisTemplate.delete(setKey1);
        }

        BoundSetOperations testSet = redisTemplate.boundSetOps(setKey);
        testSet.add("1","2","3","4");

        long size = testSet.size();
        Assert.assertEquals(size,4l);

        testSet.remove("1");
        Assert.assertEquals((long)testSet.size(),3L);
        Assert.assertTrue(!testSet.isMember("1"));

        BoundSetOperations testSet1 = redisTemplate.boundSetOps(setKey1);
        testSet1.add("2","5");

        Set<String> diff = testSet1.diff(setKey);
        Assert.assertTrue(diff.size() == 1);
        Assert.assertTrue(diff.contains("5"));
        Assert.assertTrue(!diff.contains("1"));


        Set<String> intersect = testSet1.intersect(setKey);
        Assert.assertTrue(intersect.size() == 1);
        Assert.assertTrue(intersect.contains("2"));

        Set<String> union = testSet1.union(setKey);
        Assert.assertTrue(union.size() == 4);
        Assert.assertTrue(union.contains("2"));
        Assert.assertTrue(union.contains("3"));
        Assert.assertTrue(union.contains("4"));
        Assert.assertTrue(union.contains("5"));

    }

    @Test
    public void testZSet() {
        String zSetKey = "testZSet";
        if(redisTemplate.hasKey(zSetKey)) {
            redisTemplate.delete(zSetKey);
        }

        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps(zSetKey);
        boundZSetOperations.add("hello",1);
        boundZSetOperations.add("world",2);

        Assert.assertEquals((long) boundZSetOperations.size(),2L);
        Assert.assertEquals(boundZSetOperations.range(0,0).stream().findFirst().orElse(null),"hello");


        boundZSetOperations.removeRangeByScore(1,1);
        Assert.assertEquals((long) boundZSetOperations.size(),1L);
        Assert.assertEquals(boundZSetOperations.range(0,0).stream().findFirst().orElse(null),"world");
        Assert.assertTrue(boundZSetOperations.rank("hello") == null);


    }

    @Test
    public void testHashMap() {
        String mapKey = "userRow0";
        if(redisTemplate.hasKey(mapKey)) {
            redisTemplate.delete(mapKey);
        }

        BoundHashOperations hashOps = redisTemplate.boundHashOps(mapKey);
        Map<String,String> userRow0 = Maps.newHashMap("id","0");
        userRow0.put("username","jasonliu");
        userRow0.put("password","123");
        hashOps.putAll(userRow0);

        Assert.assertTrue(hashOps.size() == 3);

        hashOps.delete("id");
        Assert.assertTrue(hashOps.size() == 2);
        Assert.assertTrue(!hashOps.hasKey("id"));

        hashOps.put("password","1");
        Assert.assertEquals(hashOps.get("password"),"1");
    }

    @Test
    public void testTransaction() {


        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set("test01","world");
                operations.delete("test02");
                operations.opsForValue().setIfPresent("watchObj",1);
                return operations.exec();
            }
        });
    }

    @Test
    public void testTransactionWithWatch() {

        if(redisTemplate.hasKey("test01") ) {
            redisTemplate.opsForValue().set("test01","hello");
        }

        if(!redisTemplate.hasKey("test02")) {
            redisTemplate.opsForValue().set("test02","world");
        }

        if(redisTemplate.hasKey("watchObj")) {
            redisTemplate.opsForValue().set("watchObj","0");
        }


        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch("watchObj");
                operations.multi();
                operations.opsForValue().set("test01","world");
                operations.delete("test02");
                operations.opsForValue().setIfPresent("watchObj",1);
                List<Object> exec = operations.exec();
                log.info("watchObj:" + operations.opsForValue().get("watchObj"));
                log.info("test02:" + operations.opsForValue().get("test01"));
                log.info("test02 is exist:" + operations.hasKey("test02"));
                log.info("###########exec result");
                exec.forEach(item -> log.info(item.toString()));
                return exec;
            }
        });
    }

    @Test
    public void testPubSub() {


    }

    @Test
    public void test() {
        List<Integer> list = Lists.emptyList();
        Set<Integer> set = Sets.newHashSet();
        Map<Object, Object> objectObjectMap = Maps.newHashMap("","");
//        objectObjectMap.remove()
    }
}
