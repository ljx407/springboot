package com.ljx.chapter7cache.service;

import com.ljx.chapter7cache.entity.User;
import com.ljx.chapter7cache.enums.SexEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private ApplicationContext applicationContext ;

    private User peggy = User.builder().name("peggy").sex(SexEnum.FEMALE).memo("world").build();
    private User jasonliu = User.builder().id(1L).name("jasonliu").sex(SexEnum.MALE).memo("hello").build();
    private User test = User.builder().id(99L).name("test").sex(SexEnum.MALE).memo("test").build();


    @Autowired
    private UserService userService;

    @Test
    public void test() {
        CacheManager bean = applicationContext.getBean(CacheManager.class);
        log.info(bean.getCacheNames().toString());
    }

    @Test
    public void testGetUserById() {
        User user = userService.getUserById(1L);
        Assert.assertNotNull(user);
        log.info(user.toString());
        Assert.assertEquals(user.getName(),"jasonliu");
    }

    @Test
    public void testGetUserByName() {
        User user = userService.getUserByName(peggy.getName());
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getMemo(),peggy.getMemo());
        Assert.assertEquals(user.getSex(),peggy.getSex());
    }

    @Test
    @Transactional
    @Rollback
    public void testInsertUser() {
        userService.insertUser(peggy);

        User user = userService.getUserByName(peggy.getName());
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getMemo(),peggy.getMemo());
        Assert.assertEquals(user.getSex(),peggy.getSex());

    }

    // 外层事务回滚并不会导致redis缓存清空，比如：当前配置了事务回滚，但是缓存依然存在
    // redis缓存插入失败，并不会导致整个事务回滚
    @Test
    @Transactional
    @Rollback
    public void testGetUserByIdCache() {
        userService.insertUserWithProperties(test);

        User user = userService.getUserById(test.getId());
        Assert.assertNotNull(user);
        log.info(user.toString());
        Assert.assertEquals(user.getName(),test.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateUser() {
        jasonliu.setMemo("hello world");
        userService.updateUser(jasonliu);

        User user = userService.getUserById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getMemo(),"hello world");
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteUser() {
        int result = userService.deleteUser(1L);
        Assert.assertTrue(result > 0);

        User user = userService.getUserById(1L);
        Assert.assertNull(user);

    }
}
