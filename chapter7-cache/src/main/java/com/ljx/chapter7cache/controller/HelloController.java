package com.ljx.chapter7cache.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        log.info("HelloController hello");
        return "hello";
    }

    @RequestMapping("/world")
    public String world() {
        log.info("HelloController world");
        return "hello";
    }
}
