package com.ljx.chapter9react.controller;

import com.ljx.chapter9react.entity.User;
import com.ljx.chapter9react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService ;

    @GetMapping("/")
    public List<User> findAllUser() {
        List<User> all = userService.findAll();
        return all ;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/")
    public User updateUser(@RequestBody User user) {

        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {

        userService.deletUser(id);
    }
}
