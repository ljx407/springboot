package com.ljx.chapter15;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@MapperScan(basePackages = "com.ljx.chapter15.dao")
@EnableAsync
@EnableCaching
public class Chapter15Application extends WebSecurityConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication.run(Chapter15Application.class, args);
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("jasonliu")
                .authorities("ROLE_ADMIN","ROLE_USER")
                .password("$2a$10$Ox42BCHxBq1KHR9JHPSeNOtj52inwp61gitP0xodyrtDF6FGRo0uK")
                .and()
                .withUser("peggy")
                .password("$2a$10$Ox42BCHxBq1KHR9JHPSeNOtj52inwp61gitP0xodyrtDF6FGRo0uK")
                .authorities("ROLE_USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] endPoints = { "auditevents","beans","conditions","configprops","env","flyway","httptrace","loggers", "liqkibase","metrics", "mappings","scheduledtasks",
                "sessions","shutdown","threaddump"};
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.to(endPoints)).hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
}
