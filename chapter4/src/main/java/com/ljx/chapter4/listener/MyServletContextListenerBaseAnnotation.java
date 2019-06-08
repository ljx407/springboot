package com.ljx.chapter4.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@Component
@WebListener
public class MyServletContextListenerBaseAnnotation implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("##### MyServletContextListenerBaseAnnotation.contextInitialized invoke!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("##### MyServletContextListenerBaseAnnotation.contextDestroyed invoke!");

    }
}
