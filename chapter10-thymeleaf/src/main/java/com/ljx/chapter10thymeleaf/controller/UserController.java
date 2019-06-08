package com.ljx.chapter10thymeleaf.controller;

import com.ljx.chapter10thymeleaf.model.User;
import com.ljx.chapter10thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @GetMapping("/")
    public String findAll(ModelMap modelMap) {
        List<User> all = userService.findAll();
        modelMap.addAttribute("users",all);
        return "users" ;
    }

    @GetMapping("/{id}")
    public ModelAndView findUserById(@PathVariable("id") Long id, ModelAndView mv) {
        User user = userService.findById(id);
        List<User> list = new ArrayList<>();
        list.add(user);
        mv.addObject("users", list);
        mv.setViewName("users");
        return mv ;
    }

    @GetMapping("/toUpdate")
    public String toUpdate() {
        return "userUpdate";
    }

    @PutMapping("/")
    public ModelAndView updateUser(User user, ModelAndView mv) {
        userService.updateUser(user);
        mv.setViewName("users");
        return mv ;
    }

    @GetMapping("/toInsert")
    public String toInsert() {
      return "userInsert";
    }

    @PostMapping("/")
    public ModelAndView insertUser(User user, ModelAndView mv) {
        userService.insertUser(user);
        mv.setViewName("users");
        return mv ;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id, ModelAndView mv) {
        userService.deleteUser(id);
        mv.setViewName("users");
        return mv ;
    }

}
