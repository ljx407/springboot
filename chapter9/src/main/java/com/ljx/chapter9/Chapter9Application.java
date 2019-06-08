package com.ljx.chapter9;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.ljx.chapter9.dao")
@EnableCaching
public class Chapter9Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter9Application.class, args);
    }

}
