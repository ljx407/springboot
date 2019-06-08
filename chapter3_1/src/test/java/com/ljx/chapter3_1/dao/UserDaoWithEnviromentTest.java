package com.ljx.chapter3_1.dao;


import com.ljx.chapter3_1.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoWithEnviromentTest {

    @Autowired
    private UserDaoWithEnviroment userDaoWithEnviroment;

    @Test
    public void testFindOne2() {
        User one = userDaoWithEnviroment.findOne(2L);
        Assert.assertNotNull(one);
        Assert.assertEquals(one.getId(),Long.valueOf(2L));
        Assert.assertEquals(one.getUserName(),"peggy");
        Assert.assertNotEquals(one.getNote(),"hello jasonliu , you are good!");

    }
}
