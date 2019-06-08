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
public class UserDaoWithDefaultTest {

    @Autowired
    private UserDaoWithDefault userDaoWithDefault;

    @Test
    public void testFindOne() {
        User one = userDaoWithDefault.findOne(1L);
        Assert.assertNotNull(one);
        Assert.assertEquals(one.getId(),Long.valueOf(1L));
        Assert.assertEquals(one.getUserName(),"jasonliu");
        Assert.assertEquals(one.getNote(),"hello jasonliu , you are good!");

    }

}
