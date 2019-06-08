package com.ljx.chapter10.controller;

import com.ljx.chapter10.model.Student;
import com.ljx.chapter10.validators.StrudentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    private StrudentValidator strudentValidator ;

    @GetMapping("/paramWithAnnotation")
    @ResponseBody
    public Map<String,Object> getParamWithAnnotation(@Valid Student student, Errors errors) {
        Map<String, Object> result = new HashMap<>();
        if(errors.hasErrors()) {
            errors.getFieldErrors().stream().forEach(item -> result.put(item.getField(),item.getDefaultMessage()));
            return result;
        }
        Arrays.stream(student.getClass().getDeclaredFields()).forEach(item -> {
            try {
                item.setAccessible(true);
                result.put(item.getName(),item.get(student));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return result ;
    }

    @InitBinder
    public void validatation(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(strudentValidator);
    }


}
