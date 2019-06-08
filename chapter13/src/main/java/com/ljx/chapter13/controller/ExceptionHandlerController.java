package com.ljx.chapter13.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    @ResponseBody
    public Map<String,Object> exceptionHandler(Exception e) {
       return ImmutableMap.of("code","40004","message",e.getMessage());
    }
}
