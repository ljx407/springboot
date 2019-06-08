package com.ljx.chapter11.controller;

import com.ljx.chapter11.model.User;
import com.ljx.chapter11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/name/{userName}")
    public List<User> findUserByUserName(@PathVariable("userName") String userName) {

        return userService.findUserByUserName(userName);
    }

    @PostMapping("/")
    public User insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

}
