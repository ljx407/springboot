package com.ljx.chapter10springmvc.service.impl;

import com.ljx.chapter10springmvc.dao.UserDao;
import com.ljx.chapter10springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;

    @Override
    public String findUserById(Long id) {
        return userDao.findUserById(id);
//        return null;
    }
}
