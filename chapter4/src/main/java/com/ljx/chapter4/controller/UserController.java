package com.ljx.chapter4.controller;

import com.ljx.chapter4.model.User;
import com.ljx.chapter4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/printUser")
    @ResponseBody
    public void printUser() {
        User user = User.builder().id(4L).userName("kele").note("success").build();
        userService.printUser(user);
        System.out.println("handler end .....");
    }
}
