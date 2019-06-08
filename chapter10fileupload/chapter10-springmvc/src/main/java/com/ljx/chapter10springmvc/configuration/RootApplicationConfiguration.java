package com.ljx.chapter10springmvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ComponentScan(
        basePackages = {"com.ljx.chapter10springmvc"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.ljx.chapter10springmvc.controller")
        }
)
@Import(MyBatisConfiguration.class)
public class RootApplicationConfiguration {



}
