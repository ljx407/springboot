package com.ljx.chapter4.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(1)
public class HelloServiceAspect1 {

    @Pointcut("execution(* com.ljx.chapter4.service.impl.HelloServiceImpl.sayHello(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void beforeAdvise() {
        log.info("##### HelloServiceAspect11 beforeAdvise ......");
    }

    @After("pointCut()")
    public void afterAdvise() {
        log.info("##### HelloServiceAspect11 afterAdvise ......");
    }

    @AfterReturning("pointCut()")
    public void afterReturingAdvise() {
        log.info("##### HelloServiceAspect11 afterReturingAdvise ......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowingAdvise() {
        log.info("##### HelloServiceAspect11 afterThrowingAdvise ......");
    }

    @Around("pointCut()")
    public void aroundAdvise(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("##### HelloServiceAspect11 aroundAdvise begin......");
        joinPoint.proceed();
        log.info("##### HelloServiceAspect11 aroundAdvise end ......");
    }
}
