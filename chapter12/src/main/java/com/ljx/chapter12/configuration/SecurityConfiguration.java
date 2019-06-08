package com.ljx.chapter12.configuration;

import com.ljx.chapter12.auth.UserAuthDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public PasswordEncoder initPasswordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public UserDetailsService initUserDetailsService() {
        return new UserAuthDetailService();
    }

//    /**
//     * 使用内存缓存
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(initPasswordEncoder())
//                .withUser("peggy")
//                .password("$2a$10$bnaJFeHxIo4EdpYBU5wVJORIX08xGjD78FyBdUBcyUx25JmWsf/Xe")
//                .roles("USER");
//
//    }


//    /**
//     * 使用数据验证
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        String userByUserName = "select user_name,pass,available from t_user where user_name = ?";
//        String authoritiesByUsernameQuery = "select u.user_name,r.role_name from t_user u inner join t_user_role ur on ur.user_id = u.id inner join t_role r ON ur.role_id = r.id where u.user_name = ? ";
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(userByUserName)
//                .authoritiesByUsernameQuery(authoritiesByUsernameQuery);
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(initUserDetailsService())
                .passwordEncoder(initPasswordEncoder());
    }

    /**
     * 配置请求授权
     * @param http
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/user/auth/*").hasAuthority("ROLE_ADMIN")
////                .anyRequest().permitAll();
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/auth/*").hasAuthority("ROLE_ADMIN")
//                .anyRequest().permitAll();
                .antMatchers("/login/page","/hello/").permitAll()
                .anyRequest().authenticated()
                .and()
                .rememberMe().key("chapter11-remember-me").tokenValiditySeconds(10)
                .and()
                .formLogin()
                .loginPage("/login/page")
                .defaultSuccessUrl("/user/")
                .and()
                .logout()
                .logoutUrl("/logout/page")
                .logoutSuccessUrl("/hello/")
                .and()
                .httpBasic();
    }

    @PostConstruct
    public void postConstruct() {
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setValueSerializer(redisTemplate.getStringSerializer());
        redisTemplate.setHashKeySerializer(redisTemplate.getStringSerializer());
    }
}
