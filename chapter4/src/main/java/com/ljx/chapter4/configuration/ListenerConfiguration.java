package com.ljx.chapter4.configuration;

import com.ljx.chapter4.listener.MyServletContextListenerBaseConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfiguration {
    @Bean
    public ServletListenerRegistrationBean initServletListenerRegistrationBean() {
        ServletListenerRegistrationBean<MyServletContextListenerBaseConfiguration> servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        MyServletContextListenerBaseConfiguration myServletContextListenerBaseConfiguration = new MyServletContextListenerBaseConfiguration();
        servletListenerRegistrationBean.setListener(myServletContextListenerBaseConfiguration);
        return servletListenerRegistrationBean;

    }
}
