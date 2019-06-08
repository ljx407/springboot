package com.ljx.chapter10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.ljx.chapter10.dao")
@EntityScan(basePackages="com.ljx.chapter10.model")
public class Chapter10Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter10Application.class, args);
    }

}
