package com.ljx.chapter11.service;

import com.ljx.chapter11.model.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);
    List<User> findUserByUserName(String userName);
    User insertUser(User user);
}
