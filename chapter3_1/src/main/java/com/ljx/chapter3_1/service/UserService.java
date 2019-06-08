package com.ljx.chapter3_1.service;

import com.ljx.chapter3_1.model.User;

public interface UserService {

    void printUser(User user);
    User findOne(Long id);


}
