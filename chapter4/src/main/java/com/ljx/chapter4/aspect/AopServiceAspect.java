package com.ljx.chapter4.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AopServiceAspect {
    @Pointcut("execution(* com.ljx.chapter4.service.AopService.*(..))")
    public void pointCut() {

    }

    @Before("pointCut() && args(username)")
    public void beforeAdvise(String username) {
        log.info("#### AopServiceAspect.beforeAdvise.....");
        log.info("#### AopServiceAspect.beforeAdvise args : {}",username);
    }

    @After("pointCut()")
    public void afterAdvise() {
        log.info("#### AopServiceAspect.afterAdvise.....");
    }

    @AfterReturning("pointCut()")
    public void afterReturingAdvise() {
        log.info("#### AopServiceAspect.afterReturingAdvise.....");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowingAdvise() {
        log.info("#### AopServiceAspect.afterThrowingAdvise.....");
    }

    @Around("pointCut()")
    public void aroundAdvise(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("#### AopServiceAspect.aroundAdvise begin.....");
        joinPoint.proceed();
        log.info("#### AopServiceAspect.aroundAdvise end.....");
    }
}
