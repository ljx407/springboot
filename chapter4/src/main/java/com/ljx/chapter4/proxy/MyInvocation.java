package com.ljx.chapter4.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyInvocation {

    private Object target ;
    private Method method;
    private Object[] args ;

    public MyInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }
}
