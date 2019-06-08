package com.ljx.chapter4.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
// 暂时没有基于interceptor的注解方式，除非自己自定义
public class LoggingInterceptorBaseAnnotation extends HandlerInterceptorAdapter {
    private String name ;

    public LoggingInterceptorBaseAnnotation(String name) {
        this.name = name ;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("##### LoggingInterceptorBaseConfiguration[{}].preHandle requestUrl:{}",name,request.getServletPath());
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("##### LoggingInterceptorBaseConfiguration[{}].postHandle invoke!",name);

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("##### LoggingInterceptorBaseConfiguration[{}].afterCompletion invoke!",name);

    }

}
