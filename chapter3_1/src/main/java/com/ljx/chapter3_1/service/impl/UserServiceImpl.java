package com.ljx.chapter3_1.service.impl;

import com.ljx.chapter3_1.dao.UserDaoWithDefault;
import com.ljx.chapter3_1.model.User;
import com.ljx.chapter3_1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoWithDefault userDaoWithDefault;

    @Override
    public void printUser(User user) {
      log.info("UserId:{},UserName:{},note:{}",user.getId(),user.getUserName(),user.getNote());
    }

    @Override
    public User findOne(Long id) {
        User user = userDaoWithDefault.findOne(id);
        printUser(user);
        return user;
    }
}
