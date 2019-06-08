package com.ljx.chapter7cache.service.impl;

import com.ljx.chapter7cache.dao.UserDao;
import com.ljx.chapter7cache.entity.User;
import com.ljx.chapter7cache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(cacheNames = "redisCache", key = "'user_' + #id")
    public User getUserById(Long id) {
        log.info("####UserServiceImpl getUserById invoke");
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        log.info("####UserServiceImpl getUserByName invoke");
        return userDao.getUserByName(name);
    }

    @Override
    @CachePut(cacheNames = "redisCache", key = "'user_' + #user.id")
    public User insertUserWithProperties(User user) {
        log.info("####UserServiceImpl insertUserWithProperties invoke");
        int result = userDao.insertUserWithProperties(user.getId(),user.getName(),user.getMemo(),user.getSex());
        return user ;
    }

    @Override
    @CachePut(cacheNames = "redisCache", key = "'user_' + #result.id")
    public User insertUser(User user) {
        log.info("####UserServiceImpl insertUser invoke");
        userDao.insertUser(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        log.info("####UserServiceImpl updateUser invoke");
        userDao.updateUser(user);
        return user;
    }

    @Override
    @CacheEvict(cacheNames = "redisCache", key = "'user_id' + #id")
    public int deleteUser(Long id) {
        log.info("####UserServiceImpl deleteUser invoke");
        return userDao.deleteUser(id);
    }
}
