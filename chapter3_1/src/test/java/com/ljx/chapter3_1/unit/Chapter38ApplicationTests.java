package com.ljx.chapter3_1.unit;

import com.ljx.chapter3_1.configuration.Chapter3_8Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Chapter38ApplicationTests {
    AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(Chapter3_8Configuration.class);
    }


    @Test
    public void contextLoad() {

        Assert.assertTrue(applicationContext.containsBean("importBrand"));

    }




}
