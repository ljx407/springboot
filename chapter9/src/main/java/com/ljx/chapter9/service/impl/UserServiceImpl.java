package com.ljx.chapter9.service.impl;

import com.ljx.chapter9.dao.UserDao;
import com.ljx.chapter9.model.User;
import com.ljx.chapter9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Cacheable(cacheNames="redisCache" , key = "'user_' + #id" )
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    @CacheEvict(cacheNames = "redisCache" , key = "'user_' + #id")
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @CachePut(cacheNames = "redisCache", key = "'user_' + #user.id" )
    public User save(User user) {
        userDao.insert(user);
        return user;
    }

    @Override
    @CacheEvict(cacheNames = "cacheRedis", key = "'user_' + #user.id", beforeInvocation = true)
    public User udpate(User user) {
        userDao.updateById(user);
        return user ;
    }


    @Override
    public User saveOrUpdate(User user) {
        if(user != null && user.getId() != null) {
            return udpate(user);
        } else {
            return save(user);
        }
    }
}
