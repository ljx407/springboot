package com.ljx.chapter10thymeleaf.service;

import com.ljx.chapter10thymeleaf.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User updateUser(User user);
    User insertUser(User user);
    void deleteUser(Long id);
}
