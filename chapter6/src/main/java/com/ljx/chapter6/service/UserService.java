package com.ljx.chapter6.service;

import com.ljx.chapter6.model.User;

public interface UserService {
    User getUserById(Long id);
    User findUserById(Long id);
    int insertUser(User user);
    int deleteUserById(Long id);
}
