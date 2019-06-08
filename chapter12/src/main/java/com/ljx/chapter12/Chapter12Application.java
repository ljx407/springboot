package com.ljx.chapter12;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ljx.chapter12.dao")
public class Chapter12Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter12Application.class, args);
    }

}
