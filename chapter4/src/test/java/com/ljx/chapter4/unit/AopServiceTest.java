package com.ljx.chapter4.unit;

import com.ljx.chapter4.configuration.AopConfiguration;
import com.ljx.chapter4.service.AopService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AopServiceTest {

    AnnotationConfigApplicationContext annotationConfigApplicationContext ;

    @Before
    public void before() {
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AopConfiguration.class);
    }

    @Test
    public void printUserTest() {
        AopService aopService = annotationConfigApplicationContext.getBean(AopService.class);
//        Arrays.stream(annotationConfigApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        aopService.printGreeting("jasonliu");
    }
}
