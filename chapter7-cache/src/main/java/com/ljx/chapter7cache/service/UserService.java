package com.ljx.chapter7cache.service;

import com.ljx.chapter7cache.entity.User;

public interface UserService {
    User getUserById(Long id);
    User insertUser(User user);
    User updateUser(User user);
    int deleteUser(Long id);
    User getUserByName(String name);
    User insertUserWithProperties(User user);

}
