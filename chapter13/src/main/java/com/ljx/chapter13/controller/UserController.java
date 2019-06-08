package com.ljx.chapter13.controller;

import com.ljx.chapter13.model.User;
import com.ljx.chapter13.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public List<User> findAllUser() {
        log.info("Current local tread name : {}" , Thread.currentThread().getName());
        return userService.findAll();
    }
}
