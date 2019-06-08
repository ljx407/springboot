package com.ljx.chapter3_1.unit;

import com.ljx.chapter3_1.configuration.Chapter3_3Configuration;
import com.ljx.chapter3_1.model.BussinessPersonWithConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
public class BussinessPersonWithConstrutorTest {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(Chapter3_3Configuration.class);

    }

    @Test
    public void testService() {
        BussinessPersonWithConstructor bussinessPersonWithConstrutor = applicationContext.getBean("bussinessPersonWithConstrutor", BussinessPersonWithConstructor.class);
        Assert.notNull(bussinessPersonWithConstrutor, "bussinessPersonWithConstrutor不能为空！");
        bussinessPersonWithConstrutor.service();
    }
}
