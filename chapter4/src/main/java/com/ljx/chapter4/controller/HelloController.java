package com.ljx.chapter4.controller;

import com.ljx.chapter4.service.HelloService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController implements ApplicationContextAware {

    private ApplicationContext context ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Autowired
    private HelloService helloService ;

    @GetMapping("/index")
    public String index() {
        helloService.sayHello("peggy");
        return "index" ;
    }

    @GetMapping("/hello")
    public String hello() {
        helloService.sayHello("peggy");
        return "index" ;
    }

    @GetMapping("/getalias")
    public ModelAndView getAlias() {
        ModelAndView mv = new ModelAndView();
        String[] helloControllers = context.getAliases("helloController");

        RequestMappingHandlerMapping requestMappingHandlerMapping = context.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

        mv.setViewName("displayAlias");
        mv.addObject("alias", Arrays.toString(helloControllers));
        return mv ;
    }

}
