package com.ljx.chapter9.service;

import com.ljx.chapter9.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id) ;

    List<User> findByUserName(String userName);

    void delete(Long id);

    User save(User user);

    User udpate(User user);

    User saveOrUpdate(User user);
}
