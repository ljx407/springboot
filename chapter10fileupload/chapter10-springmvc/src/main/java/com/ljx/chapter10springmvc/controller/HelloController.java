package com.ljx.chapter10springmvc.controller;

import com.ljx.chapter10springmvc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String index() {
        log.info("####HelloController invoke...");
        return "index";
    }

    @GetMapping("/message")
    public String hello(ModelMap modelMap) {
        Locale locale = LocaleContextHolder.getLocale();
        String greeting = messageSource.getMessage("greeting", null, locale);
        modelMap.addAttribute("greeting", greeting);
        return "hello";
    }

    @GetMapping("/testresp")
    @ResponseBody
    public User testResp() {
        return User.builder().id(1L).userName("jasonliu").memo("hello jasonliu").sex("1").build();
    }
}
