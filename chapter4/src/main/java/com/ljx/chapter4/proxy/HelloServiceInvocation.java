package com.ljx.chapter4.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class HelloServiceInvocation implements InvocationHandler {

    private Object proxyInstance ;

    public HelloServiceInvocation(Object proxyInstance) {
        this.proxyInstance = proxyInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("######HelloServiceInvocation before....");
        Object result = method.invoke(proxyInstance, args);
        log.info("######HelloServiceInvocation after...");
        return result;
    }
}
