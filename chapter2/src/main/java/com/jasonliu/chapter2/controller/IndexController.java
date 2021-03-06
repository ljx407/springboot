package com.jasonliu.chapter2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        log.info("hello world!");
        return "index";
    }
}
