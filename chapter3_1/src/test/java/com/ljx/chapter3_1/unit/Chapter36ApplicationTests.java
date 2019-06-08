package com.ljx.chapter3_1.unit;

import com.ljx.chapter3_1.configuration.Chapter3_6Configuration;
import com.ljx.chapter3_1.model.ScopeBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Chapter36ApplicationTests {
    AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(Chapter3_6Configuration.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }


    @Test
    public void scopeBeanTest() {
        ScopeBean scopeBean = applicationContext.getBean("scopeBeanWithPrototype", ScopeBean.class);
        ScopeBean scopeBean1 = applicationContext.getBean("scopeBeanWithPrototype", ScopeBean.class);
        Assert.assertFalse(scopeBean.equals(scopeBean1));
    }

    @Test
    public void scopeBeanTestWithSingleton() {
        ScopeBean scopeBean = applicationContext.getBean("scopeBeanWithSingleton", ScopeBean.class);
        ScopeBean scopeBean1 = applicationContext.getBean("scopeBeanWithSingleton", ScopeBean.class);
        Assert.assertTrue(scopeBean.equals(scopeBean1));
    }

}
