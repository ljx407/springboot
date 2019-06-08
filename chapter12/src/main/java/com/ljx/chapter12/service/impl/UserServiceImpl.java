package com.ljx.chapter12.service.impl;

import com.ljx.chapter12.dao.UserDao;
import com.ljx.chapter12.model.User;
import com.ljx.chapter12.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public User insertUser(User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        userDao.insertUser(user);
        return user;
    }

    @Override
    public User updatePaasByUsername(String username, String paas) {
        userDao.updatePaasByUserName(username,passwordEncoder.encode(paas));
        return findUserByUserName(username);
    }

    @Override
    public User updateUser(User user) {
        userDao.updateUser(user);
        return user;
    }

    @Override
    public void authUser(Long id) {
        User user = findUserById(id);
        redisTemplate.delete("user:"+user.getUserName());
        redisTemplate.delete("role:" + user.getUserName());
        redisTemplate.opsForValue().set("user:"+user.getUserName(),user.getPass());
        redisTemplate.opsForList().leftPush("role:" + user.getUserName(), "ROLE_ADMIN");
        user.setAvailable("1");
        updateUser(user);
    }
}
