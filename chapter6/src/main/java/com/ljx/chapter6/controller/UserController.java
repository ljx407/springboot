package com.ljx.chapter6.controller;

import com.ljx.chapter6.model.User;
import com.ljx.chapter6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return user.toString();
    }

    @GetMapping("/findUserById")
    public String findUserByid(Long id) {
        User user = userService.getUserById(id);
        return user.toString();
    }

    @RequestMapping("/insertUser")
    public String insertUser(User user) {
        int result = userService.insertUser(user);
        return "reuslt" + result ;
    }

}
