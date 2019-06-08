package com.ljx.chapter12.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorHandlerControler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> handleException(Exception e) {
        log.error("ErrorHandler",e);
        Map<String, Object> map = new HashMap<>();
        map.put("code","40004");
        map.put("message",e.getMessage());
        return map;
    }
}
