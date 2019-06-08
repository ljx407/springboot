package com.ljx.chapter4.configuration;

import com.ljx.chapter4.filter.MyCharacterEncodeingFilterBaseConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean initFilterRegistrationBean() {
        FilterRegistrationBean<MyCharacterEncodeingFilterBaseConfiguration> filterRegistrationBean = new FilterRegistrationBean();
        MyCharacterEncodeingFilterBaseConfiguration myCharacterEncodeingFilter = new MyCharacterEncodeingFilterBaseConfiguration();
        filterRegistrationBean.setName("MyCharacterEncodeingFilterBaseConfiguration");
        filterRegistrationBean.setFilter(myCharacterEncodeingFilter);
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


}
