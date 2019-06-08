package com.ljx.chapter3_1.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component("helloBuss")
public class BussinessPersonLifeCycle implements Person, BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private ApplicationContext applicationContext ;

    private Animal animal;

    public BussinessPersonLifeCycle(@Autowired @Qualifier("dog") Animal animal) {
        log.info("##### BussinessPersonLifeCycle contructor invoke!");
        this.animal = animal ;
    }

    @Override
    public void setBeanName(String name) {
        log.info("##### BussinessPersonLifeCycle.setBeanName invoke ! paramter name:{}",name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext ;
        log.info("##### BussinessPersonLifeCycle.setApplicationContext invoke ! paramter name:{}",applicationContext.getApplicationName());

    }

    @PostConstruct
    public void postConstruct() {
        Cat cat = applicationContext.getBean("cat", Cat.class);
        this.animal = cat ;
        log.info("#### BussinessPersonLifeCycle.initMethod invoke!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("#### BussinessPersonLifeCycle.afterPropertiesSet invoke!");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("#### BussinessPersonLifeCycle.preDestroy invoke!");
    }

    @Override
    public void destroy() throws Exception {
        log.info("#### BussinessPersonLifeCycle.destroy invoke!");

    }

    @Override
    public void service() {
        animal.use();
    }
}
