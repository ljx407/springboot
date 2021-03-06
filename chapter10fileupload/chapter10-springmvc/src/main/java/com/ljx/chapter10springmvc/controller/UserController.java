package com.ljx.chapter10springmvc.controller;

import com.ljx.chapter10springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @GetMapping("/{id}")
    @ResponseBody
    public String findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }
}
