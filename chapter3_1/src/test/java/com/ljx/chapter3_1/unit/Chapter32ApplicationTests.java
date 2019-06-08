package com.ljx.chapter3_1.unit;

import com.ljx.chapter3_1.configuration.Chapter3_2Configuration;
import com.ljx.chapter3_1.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Chapter32ApplicationTests {
    AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(Chapter3_2Configuration.class);
    }

    @Test
    public void contextLoads() {
        Assert.assertTrue(applicationContext.containsBean("user"));
        // spring will auto scan the user component and inject it
        // with class name ,first Letter lowcase
        Assert.assertFalse(applicationContext.containsBean("User"));
        Assert.assertTrue(applicationContext.containsBean("employee"));
        Assert.assertFalse(applicationContext.containsBean("indexController"));
    }

    @Test
    public void valueTest() {
        User user = applicationContext.getBean("user", User.class);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(),Long.valueOf(1L));
        Assert.assertEquals(user.getUserName(),"Peggy");
        Assert.assertEquals(user.getNote(),"HelloWorld Peggy");
    }
}
