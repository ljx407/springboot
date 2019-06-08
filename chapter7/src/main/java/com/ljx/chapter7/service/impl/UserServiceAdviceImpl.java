package com.ljx.chapter7.service.impl;

import com.ljx.chapter7.model.User;
import com.ljx.chapter7.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceAdviceImpl implements UserService {

    private Long id ;

    public UserServiceAdviceImpl() {
    }

    public UserServiceAdviceImpl(Long id) {
        this.id = id;
    }

    @Override
    public User findUserById(Long id) {
        log.info("#####UserServiceAdviceImpl.findUserById invoke.");
        return null;
    }

    @Override
    public User findUserByName(String name) {
        log.info("#####UserServiceAdviceImpl.findUserByName invoke.");
        return null;
    }
}
