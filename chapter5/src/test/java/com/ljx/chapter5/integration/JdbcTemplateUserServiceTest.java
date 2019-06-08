package com.ljx.chapter5.integration;

import com.ljx.chapter5.enums.SexEnum;
import com.ljx.chapter5.model.User;
import com.ljx.chapter5.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTemplateUserServiceTest {

    @Autowired
    @Qualifier("jdbcTemplateUserServiceImpl")
    private UserService userService;

    @Test
    public void getUserByIdTest() {
        User user = userService.getUserById(1);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getNote(),"hello");
        Assert.assertEquals(user.getUserName(),"jasonliu");
    }

    @Test
    public void getUserByIdWithStatementCallBackTest() {
        User user = userService.getUserByIdWithStatementCallBack(1);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getNote(),"hello");
        Assert.assertEquals(user.getUserName(),"jasonliu");
    }

    @Test
    public void  getUserByIdWithConnectionCallBackTest() {
        User user = userService.getUserByIdWithConnectionCallBack(1);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getNote(),"hello");
        Assert.assertEquals(user.getUserName(),"jasonliu");
    }

    @Test
    public void getUserByIdTestNull() {
        User user = userService.getUserById(-1);
        Assert.assertNull(user);
    }

    @Test
    public void findUsersTest() {
        List<User> users = userService.findUsers("e", "o");
        Assert.assertNotNull(users);
        Assert.assertEquals(users.size(),2);
        Assert.assertEquals(users.get(0).getUserName(),"peggy");
        Assert.assertEquals(users.get(1).getUserName(),"test");
    }

    @Test
    @Transactional
    @Rollback
    public void updateTest() {
        User user = User.builder().id(1).userName("jasonliu111").note("hello").sex(SexEnum.MAIL).build();
        int update = userService.update(user);
        Assert.assertEquals(update,1);
    }

    @Test
    @Transactional
    @Rollback
    public void innsertTest() {
        User user = User.builder().userName("test001").note("test001").sex(SexEnum.MAIL).build();
        int result = userService.insert(user);
        Assert.assertEquals(result,1);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteTest() {
        User user = User.builder().id(1).build();
        int result = userService.delete(user);
        Assert.assertEquals(result,1);

        User userNotExist = userService.getUserById(1);
        Assert.assertNull(userNotExist);
    }

}
