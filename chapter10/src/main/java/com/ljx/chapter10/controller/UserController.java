package com.ljx.chapter10.controller;

import com.ljx.chapter10.model.User;
import com.ljx.chapter10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.findUsers();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/")
    @ResponseBody
    public User insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PutMapping("/")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }
}
