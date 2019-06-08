package com.ljx.chapter4.proxy;

import com.ljx.chapter4.interceptor.MyInterceptor;
import com.ljx.chapter4.service.HelloService;
import com.ljx.chapter4.service.impl.HelloServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MyProxyBeanTest {

    @Test
    public void testReturning() {
        HelloService helloService = new HelloServiceImpl();
        MyInterceptor myInterceptor = new MyInterceptor();
        HelloService helloServiceProxy = MyProxyBean.newInstance(helloService, myInterceptor);
        helloServiceProxy.sayHello("jasonliu");
    }

    @Test
    public void testThrowing() {
        HelloService helloService = new HelloServiceImpl();
        MyInterceptor myInterceptor = new MyInterceptor();
        HelloService helloServiceProxy = MyProxyBean.newInstance(helloService, myInterceptor);
        helloServiceProxy.sayHello(null);
    }
}
