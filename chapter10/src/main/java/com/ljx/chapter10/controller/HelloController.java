package com.ljx.chapter10.controller;

import com.ljx.chapter10.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/paramWithoutAnnotation")
    @ResponseBody
    public Map<String,Object> getParamWithoutAnnotation(String name,Long age,String memo) {
        Map<String, Object> result = getResultMap(name, age, memo);
        return result ;
    }

    @GetMapping("/paramWithAnnotation")
    @ResponseBody
    public Map<String,Object> getParamWithAnnotation(@RequestParam("user_name") String name, @RequestParam("user_age") Long age, @RequestParam("user_memo") String memo) {
        Map<String, Object> result = getResultMap(name, age, memo);
        return result ;
    }

    @GetMapping("/paramWithAnnotationrequired")
    @ResponseBody
    public Map<String,Object> getParamWithAnnotation1(@RequestParam(name = "user_name", required = false) String name, @RequestParam(name = "user_age", required = false) Long age, @RequestParam(name = "user_memo", required = false) String memo) {
        Map<String, Object> result = getResultMap(name, age, memo);
        return result ;
    }

    @GetMapping("/paramWithArray")
    @ResponseBody
    public Map<String,Object> getParamWithArray(String[] name,Long[] age,String[] memo) {
        Map<String, Object> result = getResultMap(name, age, memo);
        return result ;
    }

    @GetMapping("/paramWithJason")
    @ResponseBody
    public User getParamWithJason(@RequestBody User user) {
        log.info(user.toString());
        return user ;
    }

    @GetMapping("/paramWithForm")
    @ResponseBody
    public User getParamWithJason1(User user) {
        log.info(user.toString());
        return user ;
    }

    @GetMapping("/{name}")
    @ResponseBody
    public Map<String, Object> getParamWithPathVariable(@PathVariable("name") String name) {
        Map<String, Object> resultMap = getResultMap(name, null, null);
        return resultMap ;
    }

    @GetMapping("/paramWithFormat")
    @ResponseBody
    public Map<String, Object> getParamWithFormat(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE ,pattern="yyyy-MM-dd HH:mm:ss") Date date, @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = ",###.00") Double money) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("date",date);
        resultMap.put("money",money);
        return resultMap;

    }

    private Map<String, Object> getResultMap(Object name, Object age, Object memo) {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("age", age);
        result.put("memo", memo);
        return result;
    }


}
