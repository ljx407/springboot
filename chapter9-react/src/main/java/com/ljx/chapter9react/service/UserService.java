package com.ljx.chapter9react.service;

import com.ljx.chapter9react.entity.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    void deletUser(Long id);

    User updateUser(User user);

    List<User> findAll();

    User findUserById(Long id);
}
