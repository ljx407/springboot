package com.ljx.chapter16.configuration;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("jasonliu")
                .authorities("ROLE_ADMIN")
                .password("$2a$10$bB8HItF133RUdjAvXbBG.uhzX2eb34/s6z3ZFMEcN.Tde11tbJ0QK")
                .and()
                .withUser("peggy")
                .password("$2a$10$bB8HItF133RUdjAvXbBG.uhzX2eb34/s6z3ZFMEcN.Tde11tbJ0QK")
                .authorities("ROLE_USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] endPoints = { "auditevents","beans","conditions","configprops","env","flyway","httptrace","loggers", "liqkibase","metrics", "mappings","scheduledtasks",
                "sessions","shutdown","threaddump","ljxendpoint"};
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.to(endPoints)).hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

    }
}
