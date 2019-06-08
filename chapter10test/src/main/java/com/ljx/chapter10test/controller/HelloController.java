package com.ljx.chapter10test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/")
    public String index() {
        log.info("######log HelloController index");
        return "hello";
    }
}
