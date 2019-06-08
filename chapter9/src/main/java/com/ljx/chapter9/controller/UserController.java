package com.ljx.chapter9.controller;

import com.ljx.chapter9.model.User;
import com.ljx.chapter9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/findAll")
    public ModelAndView findAllUser() {
        List<User> users = userService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/usertable");
        mv.addObject("users",users);
        return mv;
    }


    @RequestMapping("/findByUserName")
    public ModelAndView findByUserName(@RequestParam("userName") String userName) {
        List<User> users = userService.findByUserName(userName);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/usertable");
        mv.addObject("users",users);
        return mv;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("id") Long id) {
        User user = userService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/userdetail");
        mv.addObject("user",user);
        return mv;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView update(@RequestParam("id") Long id) {
        ModelAndView mv = detail(id);
        mv.setViewName("user/userupdate");
        return mv;
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/useradd";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/save")
    public String save(User user) {
        if(user != null && user.getId() != null) {
            userService.udpate(user);
        } else {
            userService.save(user);
        }
        return "redirect:/user/findAll";
    }


}
