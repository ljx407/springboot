package com.ljx.chapter10.service;

import com.ljx.chapter10.model.User;

import java.util.List;

public interface UserService {
    List<User> findUsers();
    User findUserById(Long id);
    User insertUser(User user);
    User updateUser(User User);
    void deleteUserById(Long id);
}
