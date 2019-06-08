package com.ljx.chapter3_1.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("database.testb")
@PropertySource("classpath:jdbc2.properties")
public class TestBProperties extends BaseDataSource{

}
