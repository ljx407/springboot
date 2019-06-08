package com.ljx.chapter7.controller;

import com.ljx.chapter7.model.User;
import com.ljx.chapter7.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return user.toString();
    }
}
