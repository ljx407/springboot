package com.ljx.chapter9react;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan("com.ljx.chapter9react.entity")
@MapperScan({"com.ljx.chapter9react.mapper","com.ljx.chapter9react.dao"})
public class Chapter9ReactApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter9ReactApplication.class, args);
    }

}
