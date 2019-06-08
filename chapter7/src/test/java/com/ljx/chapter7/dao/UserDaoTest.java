package com.ljx.chapter7.dao;

import com.ljx.chapter7.enums.SexEnum;
import com.ljx.chapter7.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao ;

    @Test
    public void findByIdTest() {
        User user = userDao.getUserById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUsername(),"jasonliu");
        Assert.assertEquals(user.getSex(), SexEnum.MALE);
        Assert.assertEquals(user.getNote(),"hello");
    }
}
