package com.ljx.chapter3_1.unit;

import com.ljx.chapter3_1.configuration.Chapter3_3Configuration;
import com.ljx.chapter3_1.model.BussinessPersonLifeCycle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BussinessPersonLifeCycleTest {
    AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(Chapter3_3Configuration.class);
    }

    @Test
    public void serviceTest() {
        BussinessPersonLifeCycle bussinessPerson = applicationContext.getBean("helloBuss", BussinessPersonLifeCycle.class);
        bussinessPerson.service();
    }

    @After
    public void tearDown() {
        applicationContext.close();
    }
}
