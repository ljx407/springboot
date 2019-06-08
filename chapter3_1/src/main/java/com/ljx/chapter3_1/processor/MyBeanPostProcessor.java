package com.ljx.chapter3_1.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("#### MyBeanPostProcessor.postProcessBeforeInitialization invoke! parameter: beanName - {}",beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("#### MyBeanPostProcessor.postProcessAfterInitialization invoke! parameter: beanName - {}",beanName);
        return null;
    }
}
