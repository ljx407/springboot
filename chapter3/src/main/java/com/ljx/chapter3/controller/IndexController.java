package com.ljx.chapter3.controller;

import com.ljx.chapter3.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    @Qualifier("user")
    private User user ;

    @RequestMapping("index")
    public ModelAndView index() {
        log.info("index coming....");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("user",user);
        return mv;
    }
}
