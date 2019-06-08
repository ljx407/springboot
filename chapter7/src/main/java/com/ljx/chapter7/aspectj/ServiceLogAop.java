package com.ljx.chapter7.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ServiceLogAop {

    @Pointcut("execution(* com.ljx.chapter7.service.impl.UserServiceImpl.*(..))")
    public void pointCut() {

    }


    @Before(value ="pointCut()")
    public void beforeAdvice() {
        log.info("method beging ......");
    }

    @After(value = "pointCut()")
    public void afterAdvice() {
        log.info("method end .......");
    }
}
