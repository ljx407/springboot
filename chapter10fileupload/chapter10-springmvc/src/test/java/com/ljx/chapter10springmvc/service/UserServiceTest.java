package com.ljx.chapter10springmvc.service;

import com.ljx.chapter10springmvc.configuration.RootApplicationConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private static AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext ;

    @BeforeClass
    public static void before() {
        annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(RootApplicationConfiguration.class);
    }

    @Test
    public void findUserByIdTest() {
        UserService userService = annotationConfigWebApplicationContext.getBean(UserService.class);
//        User user = userService.findUserById(1L);
//        Assert.notNull(user);
    }
}
