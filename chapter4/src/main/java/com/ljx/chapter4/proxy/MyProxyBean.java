package com.ljx.chapter4.proxy;

import com.ljx.chapter4.interceptor.MyInterceptor;
import com.ljx.chapter4.service.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxyBean implements InvocationHandler {

    private static MyProxyBean myProxyBean = null ;

    private Object target ;

    private Object interceptor;

    private MyProxyBean() {

    }

    public MyProxyBean(Object target, Object interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    public static HelloService newInstance(HelloService helloService, MyInterceptor interceptor) {
        myProxyBean = new MyProxyBean(helloService,interceptor);
        return (HelloService) Proxy.newProxyInstance(helloService.getClass().getClassLoader(), helloService.getClass().getInterfaces(), myProxyBean);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyInterceptor myInterceptor = (MyInterceptor) interceptor;

        MyInvocation myInvocation = new MyInvocation(target,method,args);

        boolean hasException = false ;
        Object result = null ;

        try {
            myInterceptor.before();

            if (myInterceptor.useAround()) {
                result = myInterceptor.around(myInvocation);
            } else {
                result = myInvocation.proceed();
            }

        } catch (Exception e) {
            hasException = true ;
        }

        myInterceptor.after();

        if(hasException) {
            myInterceptor.afterThrowing();
        } else {
            myInterceptor.afterReturning();
        }

        return result ;
    }
}
