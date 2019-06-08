package com.ljx.chapter7.service;

import com.ljx.chapter7.model.User;

public interface UserService {
    User findUserById(Long id);

    User findUserByName(String name);
}
