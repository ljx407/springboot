package com.ljx.chapter8springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableConfigurationProperties
public class Chapter8SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter8SpringbootApplication.class, args);
    }

}
