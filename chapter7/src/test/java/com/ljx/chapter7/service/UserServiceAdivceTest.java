package com.ljx.chapter7.service;

import com.ljx.chapter7.configuration.AdviceConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
public class UserServiceAdivceTest {

    private static AnnotationConfigApplicationContext applicationContext ;

    @BeforeClass
    public static void beforeClass() {
        applicationContext = new AnnotationConfigApplicationContext(AdviceConfiguration.class);
    }

    @Test
    public void findByNameTest() {
        UserService userServiceProxy = (UserService)applicationContext.getBean("userServiceProxy");
        userServiceProxy.findUserById(1L);
        System.out.println("######" + userServiceProxy.getClass());
    }

    @Test
    public void testFactoryBeanCreate() {
        Object userServiceProxy = applicationContext.getBean("userServiceFactoryBean");
        System.out.println("######" + userServiceProxy.getClass());
    }

    @Test
    public void testAdvicedSupport() {
        AdvisedSupport bean = applicationContext.getBean(AdvisedSupport.class);
        Arrays.stream(bean.getAdvisors()).forEach(System.out::println);
    }
}
