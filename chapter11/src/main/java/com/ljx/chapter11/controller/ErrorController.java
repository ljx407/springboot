package com.ljx.chapter11.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String,Object> handleException(Exception e) {
        log.error("ErrorController error",e);
        Map<String, Object> result = new HashMap<>();
        result.put("code",HttpStatus.INTERNAL_SERVER_ERROR);
        result.put("message",e.getMessage());
        return result ;
    }
}
