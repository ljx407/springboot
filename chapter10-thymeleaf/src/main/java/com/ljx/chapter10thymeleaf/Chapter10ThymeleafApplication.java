package com.ljx.chapter10thymeleaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ljx.chapter10thymeleaf.dao")
public class Chapter10ThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter10ThymeleafApplication.class, args);
    }

}
