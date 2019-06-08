package com.ljx.chapter7.advices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

@Slf4j
public class UserServiceBeforeAdivce implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.info("######UserServiceBeforeAdivce.before........");
    }
}
