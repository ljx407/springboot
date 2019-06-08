package com.ljx.chapter13.service.impl;

import com.ljx.chapter13.dao.UserDao;
import com.ljx.chapter13.model.User;
import com.ljx.chapter13.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        log.info("current thread name : {}" , Thread.currentThread().getName());
        return userDao.findAllUser();
    }
}
