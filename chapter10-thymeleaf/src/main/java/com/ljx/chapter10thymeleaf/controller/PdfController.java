package com.ljx.chapter10thymeleaf.controller;

import com.ljx.chapter10thymeleaf.service.UserService;
import com.ljx.chapter10thymeleaf.views.PdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("pdf")
public class PdfController {

    @Autowired
    private UserService userService;

    @GetMapping("/show")
    public ModelAndView showPdf(ModelAndView mv) {
        mv.addObject("users",userService.findAll());
        mv.setView(new PdfView());
        return mv ;
    }
}
