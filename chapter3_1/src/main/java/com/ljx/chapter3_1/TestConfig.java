package com.ljx.chapter3_1;

import com.ljx.chapter3_1.configuration.Chapter3_3Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class TestConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Chapter3_3Configuration.class);
        applicationContext.refresh();
//        User user = applicationContext.getBean("user", User.class);
//        log.info("UserName:{}",user.getUserName());
        filterBean(applicationContext);
    }

    public static void filterBean(AnnotationConfigApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getBeanClassName() != null && beanDefinition.getBeanClassName().contains("ljx")){
                System.out.println(beanDefinitionName);
            }
        }
    }
}
