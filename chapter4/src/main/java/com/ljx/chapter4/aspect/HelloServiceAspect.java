package com.ljx.chapter4.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(2)
public class HelloServiceAspect {

    @Before("execution(* com.ljx.chapter4.service.impl.HelloServiceImpl.sayHello(..))")
    public void beforeAdvise() {
        log.info("##### HelloServiceAspect beforeAdvise ......");
    }

    @After("execution(* com.ljx.chapter4.service.impl.HelloServiceImpl.sayHello(..))")
    public void afterAdvise() {
        log.info("##### HelloServiceAspect afterAdvise ......");
    }

    @AfterReturning("execution(* com.ljx.chapter4.service.impl.HelloServiceImpl.sayHello(..))")
    public void afterReturingAdvise() {
        log.info("##### HelloServiceAspect afterReturingAdvise ......");
    }

    @AfterThrowing("execution(* com.ljx.chapter4.service.impl.HelloServiceImpl.sayHello(..))")
    public void afterThrowingAdvise() {
        log.info("##### HelloServiceAspect afterThrowingAdvise ......");
    }

    @Around("execution(* com.ljx.chapter4.service.impl.HelloServiceImpl.sayHello(..))")
    public void aroundAdvise(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("##### HelloServiceAspect aroundAdvise begin......");
        joinPoint.proceed();
        log.info("##### HelloServiceAspect aroundAdvise end ......");
    }
}
