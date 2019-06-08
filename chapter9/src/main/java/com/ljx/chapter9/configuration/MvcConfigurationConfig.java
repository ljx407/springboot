package com.ljx.chapter9.configuration;

import com.ljx.chapter9.converts.StringToSexEnumConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurationConfig implements WebMvcConfigurer {

    @Autowired
    private StringToSexEnumConvert stringToSexEnumConvert;

    @Override
    @ConditionalOnBean(StringToSexEnumConvert.class)
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToSexEnumConvert);
    }
}
