package com.ljx.chapter4.aspect;

import com.ljx.chapter4.aspect.introduction.UserServiceParameterValidator;
import com.ljx.chapter4.aspect.introduction.impl.UserServiceParameterValidatorImpl;
import com.ljx.chapter4.model.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class UserServiceAspect {

    @DeclareParents(
            value = "com.ljx.chapter4.service.impl.UserServiceImpl",
            defaultImpl = UserServiceParameterValidatorImpl.class
    )
    private UserServiceParameterValidator userServiceParameterValidator;

    @Pointcut("execution(* com.ljx.chapter4.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut() {

    }

    @Before("pointCut() && args(user) && this(userServiceParameterValidator)")
    public void beforeAdvise(JoinPoint joinPoint , User user ,UserServiceParameterValidator userServiceParameterValidator) {
        if(userServiceParameterValidator.paramterValidator(user)) {
            log.info("##### UserServiceAspect beforeAdvise paramterValidator pass.....");
        }
        log.info("##### UserServiceAspect beforeAdvise args {}",joinPoint.getArgs());
        log.info("##### UserServiceAspect beforeAdvise args2 {}",user.toString());
        log.info("##### UserServiceAspect beforeAdvise ......");
    }

    @After("pointCut()")
    public void afterAdvise() {
        log.info("##### UserServiceAspect afterAdvise ......");
    }

    @AfterReturning("pointCut()")
    public void afterReturingAdvise() {
        log.info("##### UserServiceAspect afterReturingAdvise ......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowingAdvise() {
        log.info("##### UserServiceAspect afterThrowingAdvise ......");
    }

    @Around("pointCut()")
    public void aroundAdvise(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("##### UserServiceAspect aroundAdvise begin......");
        joinPoint.proceed();
        log.info("##### UserServiceAspect aroundAdvise end ......");
    }
}
