package com.ljx.chapter5.integration;

import com.ljx.chapter5.model.User;
import com.ljx.chapter5.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserByIdTest() {
        User user = userService.getUserById(1);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getNote(),"hello");
        Assert.assertEquals(user.getUserName(),"jasonliu");
    }

}
