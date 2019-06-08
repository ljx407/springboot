package com.ljx.chapter3.configuration;

import com.ljx.chapter3.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class Chapter3Configuration {

    @Value("${username}")
    private String username ;

    @Autowired
    private Environment environment;

    @Bean("userEnv")
    public User initUserFromEnviroment() {
        return User.builder().userName(environment.getProperty("username")).build();

    }

    @Bean("userEnv2")
    public User initUserFromEnviroment2() {
        return User.builder().userName(username).build();

    }

    @Bean("user")
    @Profile("dev")
    public User initDevUser() {
        return User.builder()
                .userId(1L)
                .userName("jasonliu")
                .note("engineer")
                .build();
    }

    @Bean("user")
    @Profile("test")
    public User initTestUser() {
        return User.builder()
                .userId(1L)
                .userName("peggy")
                .note("human resource")
                .build();
    }

}
