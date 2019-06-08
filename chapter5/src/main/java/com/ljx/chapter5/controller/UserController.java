package com.ljx.chapter5.controller;

import com.ljx.chapter5.model.User;
import com.ljx.chapter5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/{id}")
    @ResponseBody
    public String getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return user.toString() ;
    }

}
