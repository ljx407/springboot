package com.ljx.chapter4.proxy;

import com.ljx.chapter4.service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MyJdbcServiceProxyBean implements InvocationHandler {
    private Object target ;
    private Object MyInterceptor ;

    private MyJdbcServiceProxyBean(Object target, Object myInterceptor) {
        this.target = target;
        MyInterceptor = myInterceptor;
    }

    public static UserService newInstance(UserService target, Object myInterceptor) {
        MyJdbcServiceProxyBean myJdbcServiceProxyBean = new MyJdbcServiceProxyBean(target,myInterceptor);
        return (UserService) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),myJdbcServiceProxyBean);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Connection connecton = null ;
        PreparedStatement preparedStatement = null ;
        try {
            connecton = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","root","root");
            connecton.setAutoCommit(false);
            method.invoke(target,args);
            connecton.commit();

        } catch (Exception e) {

        } finally {
            preparedStatement.close();
            connecton.commit();
        }
        return null;
    }
}
