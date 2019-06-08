package com.ljx.chapter3_1.dao;

import com.ljx.chapter3_1.model.User;

public interface UserDao {
    User findOne(Long id);
}
