package com.ljx.chapter15.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class CustomAsyncUncaoughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("method:{} with param : {}, throw exception : {}",method.getName(), params, ex.getMessage());
        log.error(ex.getMessage(),ex);
    }
}
