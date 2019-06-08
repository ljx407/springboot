package com.ljx.chapter4.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
public class MyServletContextListenerBaseConfiguration implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("##### MyServletContextListenerBaseConfiguration.contextInitialized invoke!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("##### MyServletContextListenerBaseConfiguration.contextDestroyed invoke!");

    }
}
