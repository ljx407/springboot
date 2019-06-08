package com.ljx.chapter3;

import com.ljx.chapter3.configuration.Chapter3Configuration;
import com.ljx.chapter3.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
public class Chapter3ApplicationTests {

    private AnnotationConfigApplicationContext annotationConfigApplicationContext ;

    @Before
    public void setUp() {
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Chapter3Configuration.class);
    }

    @Test
    public void contextLoads() {
        Assert.notNull(annotationConfigApplicationContext.getBean("userEnv"),"userEnv is null");
        User userEnv = annotationConfigApplicationContext.getBean("userEnv", User.class);
        Assert.isNull(userEnv.getUserName(),"username is not null");

        User userEnv2 = annotationConfigApplicationContext.getBean("userEnv2", User.class);
        Assert.isTrue("jasonliu".equals(userEnv2.getUserName()),"username is not equals");
    }

}

