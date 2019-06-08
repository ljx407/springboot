package com.ljx.chapter4.integration;

import com.ljx.chapter4.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Autowired
    private HelloService helloService ;

    @Test
    public void sayHelloTest() {

        helloService.sayHello("jasonliu");
        System.out.println("test overing...");
    }
}
