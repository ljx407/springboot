package com.ljx.chapter11.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan(
        basePackages = "com.ljx.chapter11",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebMvcConfiguration.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, RestController.class})
        }
)
public class RootApplicationConfiguration {
}
