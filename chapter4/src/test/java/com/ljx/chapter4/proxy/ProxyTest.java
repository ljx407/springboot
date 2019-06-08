package com.ljx.chapter4.proxy;

import com.ljx.chapter4.service.HelloService;
import com.ljx.chapter4.service.impl.HelloServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;

@RunWith(SpringRunner.class)
public class ProxyTest {

    @Test
    public void testProxy() {
        HelloService helloService = new HelloServiceImpl();
        HelloServiceInvocation helloServiceInvocation = new HelloServiceInvocation(helloService);
        HelloService proxyInstance = (HelloService)Proxy.newProxyInstance(helloService.getClass().getClassLoader(), helloService.getClass().getInterfaces(), helloServiceInvocation);
        proxyInstance.sayHello("jsaonliu");
    }
}
