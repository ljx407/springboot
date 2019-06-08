package com.ljx.chapter10thymeleaf.service.impl;

import com.ljx.chapter10thymeleaf.dao.UserDao;
import com.ljx.chapter10thymeleaf.model.User;
import com.ljx.chapter10thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Cacheable(cacheNames = "users",key = "#id")
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    @CachePut(cacheNames = "users", key = "#result.id")
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    @CachePut(cacheNames = "users", key = "#result.id")
    public User insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    @CacheEvict(cacheNames = "users", key = "#id" ,beforeInvocation = true)
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
