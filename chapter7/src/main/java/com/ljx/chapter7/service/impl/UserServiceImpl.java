package com.ljx.chapter7.service.impl;

import com.ljx.chapter7.dao.UserDao;
import com.ljx.chapter7.enums.SexEnum;
import com.ljx.chapter7.model.User;
import com.ljx.chapter7.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;

    @Override
    public User findUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User findUserByName(String name) {
        log.info("UserServiceImpl.findUserByName execute");
        return User.builder().id(2L).note("world").username(name).sex(SexEnum.MALE).build();
    }
}
