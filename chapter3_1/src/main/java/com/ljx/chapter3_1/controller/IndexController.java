package com.ljx.chapter3_1.controller;

import com.ljx.chapter3_1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject(userService.findOne(1L));
        mv.addObject("indexControler", this.toString());
        log.info(this.toString());
        return mv;
    }
}
