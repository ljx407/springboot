package com.ljx.chapter7.controller;

import com.ljx.chapter7.factorybeans.UserServiceFactoryBean;
import com.ljx.chapter7.model.User;
import com.ljx.chapter7.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/userAdvice")
public class UserAdviceController {

    @Autowired
    private ProxyFactoryBean proxyFactoryBean ;

    @Autowired
    @Qualifier("userServiceAdvice")
    private UserService userService;

    @Autowired
    @Qualifier("userServiceFactoryBean")
    private UserServiceFactoryBean userServiceFactoryBean ;

    @Autowired
    @Qualifier("userServiceFactoryBean")
    private UserService userServiceFactoryBeanObject ;



    @RequestMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id) throws Exception {
        User user = userService.findUserById(id);
        Object object = proxyFactoryBean.getObject();
        userServiceFactoryBeanObject.findUserById(1L);
        UserService object1 = userServiceFactoryBean.getObject();
//        UserService object1 = userServiceFactoryBean.getObject();
        return null;
    }
}
