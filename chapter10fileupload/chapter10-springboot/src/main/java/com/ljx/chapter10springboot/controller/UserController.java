package com.ljx.chapter10springboot.controller;

import com.ljx.chapter10springboot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@SessionAttributes(value = "user")
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public String findUserById(@PathVariable("id") Long id, Model model , HttpSession httpSession) {
        model.addAttribute("name",id);
        model.addAttribute("user", User.builder().id(1L).username("jsoanliu").build());
        httpSession.setAttribute("sex","1");
        return "user";
    }


    @GetMapping("/httphead")
    @ResponseBody
    public String httphead(@RequestHeader("Cookie") String contentType) {
        return contentType;
    }


    @GetMapping("/testex")
    @ResponseBody
    public User testex(@ModelAttribute("user") User user, @RequestParam(value = "exflag",required = false) String exflag) {
        if(exflag != null && "1".equalsIgnoreCase(exflag)) {
            throw new RuntimeException("testex throw");
        }
        return user;
    }

}

