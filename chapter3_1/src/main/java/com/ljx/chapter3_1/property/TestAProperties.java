package com.ljx.chapter3_1.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:jdbc2.properties")
@ConfigurationProperties("database.testa")
public class TestAProperties extends BaseDataSource {

}
