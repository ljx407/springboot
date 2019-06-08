package com.ljx.chapter9.controller;

import com.ljx.chapter9.model.User;
import com.ljx.chapter9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService ;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView findAllUser() {
        List<User> users = userService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/usertable");
        mv.addObject("users",users);
        return mv;
    }


    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ModelAndView findByUserName(@PathVariable("username") String userName) {
        List<User> users = userService.findByUserName(userName);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/usertable");
        mv.addObject("users",users);
        return mv;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/userdetail");
        mv.addObject("user",user);
        return mv;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user/findAll";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String save(User user) {
        if(user != null && user.getId() != null) {
            userService.udpate(user);
        } else {
            userService.save(user);
        }
        return "redirect:/user/findAll";
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String update(User user) {
        if(user != null && user.getId() != null) {
            userService.udpate(user);
        } else {
            userService.save(user);
        }
        return "redirect:/user/findAll";
    }


}
