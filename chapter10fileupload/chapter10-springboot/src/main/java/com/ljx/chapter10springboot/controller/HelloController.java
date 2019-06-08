package com.ljx.chapter10springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String hello(String name, @SessionAttribute("memo") String memo, Model model) {
        log.info("#####HelloController memo {}" , memo);
        name = name == null ? "peggy" : name ;
        Locale locale = LocaleContextHolder.getLocale();
        String greeting = messageSource.getMessage("greeting", new Object[]{name}, locale);
        model.addAttribute("greeting",greeting);
        return "hello";
    }

    @GetMapping("/direct")
    public String world(RedirectAttributes redirectAttributes) {

        log.info("HelloController world.....");
        redirectAttributes.addAttribute("name","jasonliu");
        return "redirect:/hello/";
    }

}
