package com.ljx.chapter8springboot.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
public class ForthMongoTemplateConfiguration extends AnotherAbstractMongoConfiguration {

    @Bean("forthMongoTemplate")
    @Override
    public MongoTemplate initMongoTemplate() {
        return new MongoTemplate(getMongoClient(),getDatabase());
    }
}
