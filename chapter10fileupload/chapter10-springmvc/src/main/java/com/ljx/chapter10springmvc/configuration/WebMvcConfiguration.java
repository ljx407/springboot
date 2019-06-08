package com.ljx.chapter10springmvc.configuration;

import com.ljx.chapter10springmvc.interceptors.GreetingInterceptor;
import com.ljx.chapter10springmvc.interceptors.GreetingInterceptor2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ljx.chapter10springmvc.controller")
@PropertySource("classpath:datasource.properties")
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${username}")
    private String username;


    @Bean
    public MultipartResolver initMultipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public SpringResourceTemplateResolver initSpringResourceTemplateResolver() {
        System.out.println(username);
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setCacheable(false);
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setPrefix("/WEB-INF/views/");
        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
        return springResourceTemplateResolver;
    }

    @Bean
    public SpringTemplateEngine initTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(initSpringResourceTemplateResolver());
        return templateEngine;
    }

    @Bean
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(initTemplateEngine());
        thymeleafViewResolver.setOrder(1);
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return thymeleafViewResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new GreetingInterceptor());
        interceptorRegistration.addPathPatterns("/hello/*");

        InterceptorRegistration interceptorRegistration2 = registry.addInterceptor(new GreetingInterceptor2());
        interceptorRegistration2.addPathPatterns("/hello/*");

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("peggy");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean("localeResolver")
    public LocaleResolver initLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINESE);
        return cookieLocaleResolver;
    }

    @Bean("messageSource")
    public MessageSource initMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("peggy");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


}
