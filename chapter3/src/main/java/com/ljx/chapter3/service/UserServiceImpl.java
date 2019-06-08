package com.ljx.chapter3.service;

import com.ljx.chapter3.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private User user;

    @Override
    public String helloUser() {
        return "hello " + user.getUserName();
    }
}
