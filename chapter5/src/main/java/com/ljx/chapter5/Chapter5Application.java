package com.ljx.chapter5;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@ComponentScan(
        basePackages = "com.ljx.chapter5",
        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = Chapter5Configuration.class)
                @ComponentScan.Filter(type = FilterType.REGEX,pattern="com.ljx.chapter5.configuration.*")
        })
@SpringBootApplication
@EnableJpaRepositories("com.ljx.chapter5.dao")
@EntityScan("com.ljx.chapter5.model")
@MapperScan(
        basePackages = "com.ljx.chapter5.mapper"
        ,annotationClass = Repository.class
//        ,sqlSessionFactoryRef = "sqlSessionFactory"
//        ,sqlSessionTemplateRef = "sqlSessionTemplate"
)

public class Chapter5Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter5Application.class, args);

    }
}
