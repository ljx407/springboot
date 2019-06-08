package com.ljx.chapter9react.service.impl;

import com.ljx.chapter9react.entity.User;
import com.ljx.chapter9react.mapper.UserMapper;
import com.ljx.chapter9react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper ;

    @Override
    public User addUser(User user) {
        return userMapper.save(user);
    }

    @Override
    public void deletUser(Long id) {

        userMapper.deleteById(id);
    }

    @Override
    public User updateUser(User user) {

        return userMapper.save(user);
    }

    @Override
    public List<User> findAll() {

        return userMapper.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.findById(id).orElse(null);
    }
}
