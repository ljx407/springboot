package com.ljx.chapter7.configuration;

import com.ljx.chapter7.advices.UserServiceAfterAdvice;
import com.ljx.chapter7.advices.UserServiceBeforeAdivce;
import com.ljx.chapter7.factorybeans.UserServiceFactoryBean;
import com.ljx.chapter7.service.UserService;
import com.ljx.chapter7.service.impl.UserServiceAdviceImpl;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdviceConfiguration {

    @Bean("userServiceFactoryBean")
    public UserServiceFactoryBean initUserServiceFactoryBean() {
        return new UserServiceFactoryBean();
    }

    @Bean("userServiceProxy")
    public ProxyFactoryBean initProxyFacotryBean() throws ClassNotFoundException {
        UserService userServiceAdvice = new UserServiceAdviceImpl() ;
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(userServiceAdvice);
        proxyFactoryBean.setProxyInterfaces(new Class[]{UserService.class});
        proxyFactoryBean.setProxyTargetClass(false);
        proxyFactoryBean.addAdvice(new UserServiceBeforeAdivce());

        UserServiceAfterAdvice userServiceAfterAdvice = new UserServiceAfterAdvice();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(userServiceAfterAdvice);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.ljx.chapter7.service.impl.UserServiceAdviceImpl.*(..))");
        advisor.setPointcut(pointcut);
        proxyFactoryBean.addAdvisor(advisor);
        return proxyFactoryBean;
    }

    @Bean("userServiceAdvice")
    public UserService initUserService() throws ClassNotFoundException {
        return (UserService)initProxyFacotryBean().getObject();
    }

}
