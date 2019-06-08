package com.ljx.chapter4.service.impl;

import com.ljx.chapter4.dao.UserDao;
import com.ljx.chapter4.model.User;
import com.ljx.chapter4.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;

@Slf4j
//@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void printUser(User user) {
        if(user == null) {
            throw new InvalidParameterException("user is null");
        }
        log.info("######username is {}",user.getUserName());
        log.info("######note is {}",user.getNote());
    }
}
