package com.ljx.chapter13.controller;

import com.ljx.chapter13.service.impl.HelloAysncServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloContoller {

    @Autowired
    private HelloAysncServiceImpl helloAysncService;

    @GetMapping
    public String hello() {
        helloAysncService.testAysnc();
        return "hello";
    }
}
