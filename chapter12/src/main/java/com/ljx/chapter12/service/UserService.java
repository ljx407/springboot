package com.ljx.chapter12.service;

import com.ljx.chapter12.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findUserById(Long id);

    User findUserByUserName(String username);

    User insertUser(User user);

    User updatePaasByUsername(String username, String paas);

    void authUser(Long id);

    User updateUser(User user);
}
