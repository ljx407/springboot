package com.ljx.chapter10springboot.controller;

import com.ljx.chapter10springboot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class MyControllerAdvice {

    @ModelAttribute("user")
    public void initModelAttribute(Model model) {
        model.addAttribute("user", User.builder().id(2L).username("peggy").build());
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {

        log.info("###### initBinder invoke");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("message",e.getMessage());
        mv.addObject("code", "4000");
        return mv ;
    }
}
