package com.ljx.chapter4.configuration;

import com.ljx.chapter4.interceptor.LoggingInterceptorBaseConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoggingInterceptorBaseConfiguration loggingInterceptorBaseConfiguration = new LoggingInterceptorBaseConfiguration("index");
        registry.addInterceptor(loggingInterceptorBaseConfiguration).addPathPatterns("/index").order(1);

        LoggingInterceptorBaseConfiguration helloLoggingInterceptorBaseConfiguration = new LoggingInterceptorBaseConfiguration("all");
        registry.addInterceptor(helloLoggingInterceptorBaseConfiguration).order(2);

    }

}
