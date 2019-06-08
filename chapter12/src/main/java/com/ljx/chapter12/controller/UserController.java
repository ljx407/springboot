package com.ljx.chapter12.controller;

import com.ljx.chapter12.model.User;
import com.ljx.chapter12.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView findAll() {
//        ModelMap mv = new ModelMap();
//        mv.put("users",userService.findAll());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("users");
        mv.addObject("users",userService.findAll());
        return mv;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User findUserById(@PathVariable("id") Long id) {

        return userService.findUserById(id);
    }

    @GetMapping("/toAddPage")
    public String toInsert() {
        return "addUser";
    }

    @GetMapping("/toUpdatePassPage")
    public ModelAndView toUpdatePass(@RequestParam("userName") String userName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("updatePass");
        mv.addObject("userName",userName);
        return mv;
    }

    @PostMapping("/")
    public String insertUser(User user) {
        userService.insertUser(user);
        return "redirect:/user/";
    }

    @PatchMapping("/")
    public String updateUserByUserName(User user) {
        userService.updatePaasByUsername(user.getUserName(),user.getPass());
        return "redirect:/user/";
    }

    @GetMapping("/auth/{id}")
    public String authUser(@PathVariable("id") Long id) {
        userService.authUser(id);
        return "redirect:/user/";
    }
}
