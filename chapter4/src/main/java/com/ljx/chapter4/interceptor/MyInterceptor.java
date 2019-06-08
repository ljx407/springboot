package com.ljx.chapter4.interceptor;

import com.ljx.chapter4.proxy.MyInvocation;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class MyInterceptor {

    public void before() {
        log.info("#### MyInterceptor.before invoke!");
    }

    public void after() {
        log.info("#### MyInterceptor.after invoke!");
    }

    public boolean useAround() {
        return true;
    }

    public Object around(MyInvocation myInvocation) throws InvocationTargetException, IllegalAccessException {
        log.info("#### MyInterceptor.around before invoke!");
        Object result = myInvocation.proceed();
        log.info("#### MyInterceptor.around after invoke!");
        return result ;
    }

    public void afterReturning() {
        log.info("#### MyInterceptor.afterReturning invoke!");
    }

    public void afterThrowing() {
        log.info("#### MyInterceptor.afterThrowing invoke!");
    }

}
