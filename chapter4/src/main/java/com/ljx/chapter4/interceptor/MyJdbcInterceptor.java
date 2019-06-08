package com.ljx.chapter4.interceptor;

import com.ljx.chapter4.proxy.MyInvocation;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class MyJdbcInterceptor {

    public Object around(MyInvocation myInvocation) throws InvocationTargetException, IllegalAccessException {
        log.info("#### MyJdbcInterceptor.around before invoke!");
        Object result = null ;
        Connection connection = null ;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","root","root");
            connection.setAutoCommit(false);
            result = myInvocation.proceed();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        log.info("#### MyJdbcInterceptor.around after invoke!");
        return result ;
    }

    public void afterReturning() {
        log.info("#### MyInterceptor.afterReturning invoke!");
    }

    public void afterThrowing() {
        log.info("#### MyInterceptor.afterThrowing invoke!");
    }

}
