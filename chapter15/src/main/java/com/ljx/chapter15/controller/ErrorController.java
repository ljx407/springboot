package com.ljx.chapter15.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ControllerAdvice
@Controller
public class ErrorController {


//    @InitBinder
//    public void intBinder() {
//
//    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> handlerException(Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code","400004");
        result.put("message",e.getMessage());
        return result;
    }
}
