package com.ljx.chapter3_1.unit;

import com.ljx.chapter3_1.configuration.Chapter3_3Configuration;
import com.ljx.chapter3_1.model.BussinessPerson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BussinessPersonTest {
    AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(Chapter3_3Configuration.class);
    }

    @Test
    public void serviceTest() {
        BussinessPerson bussinessPerson = applicationContext.getBean("bussinessPerson", BussinessPerson.class);
        bussinessPerson.service();
    }
}
