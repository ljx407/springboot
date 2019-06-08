package com.ljx.chapter3_1.unit;

import com.ljx.chapter3_1.configuration.Chapter3_5Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Chapter35ApplicationTests {
    AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(Chapter3_5Configuration.class);
    }

    @Test
    public void contextLoads() {
        // 通过@componetScan({"com.ljx.Chapter3_1"})
        // 可以看出spring默认时扫面指定包以及所有子包
        // 所以没必要配成@componetScan({"com.ljx.Chapter3_1.*"})
        Assert.assertTrue(applicationContext.containsBean("myProfileDataSourceDev"));
        Assert.assertFalse(applicationContext.containsBean("myProfileDataSourceTest"));
    }

}
