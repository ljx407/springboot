package com.ljx.chapter7;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = "com.ljx.chapter7.dao")
@EnableAspectJAutoProxy
public class Chapter7Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter7Application.class, args);
    }

}
