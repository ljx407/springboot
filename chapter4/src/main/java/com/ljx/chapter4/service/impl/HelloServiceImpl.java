package com.ljx.chapter4.service.impl;

import com.ljx.chapter4.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        Assert.hasLength(name,"parameter name is null or empty!");
        log.info("###### hello:{}", name);
    }
}
