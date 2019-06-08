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
public class UserDaoWithPropertySourceAndConfigurationPropertiesTest {

    @Autowired
    private UserDaoWithPropertySourceAndConfigurationProperties userDaoWithPropertySourceAndConfigurationProperties;


    @Test
    public void testFindOne4() {
        User one = userDaoWithPropertySourceAndConfigurationProperties.findOne(3L);
        Assert.assertNotNull(one);
        Assert.assertEquals(one.getId(),Long.valueOf(3L));
        Assert.assertEquals(one.getUserName(),"muchen");
        Assert.assertEquals(one.getNote(),"hello muchen");

    }
}
