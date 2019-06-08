package com.ljx.chapter11.service.impl;

import com.ljx.chapter11.dao.JdbcUserDao;
import com.ljx.chapter11.dao.MybatisUserDao;
import com.ljx.chapter11.mappers.AnnotationUserMapper;
import com.ljx.chapter11.model.User;
import com.ljx.chapter11.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcUserDao jdbcUserDao;

    @Autowired
    private MybatisUserDao mybatisUserDao ;

    @Autowired
    private AnnotationUserMapper annotationUserMapper;

    @Override
    public User findUserById(Long id) {
//        log.info("UserServiceImpl.findUserById id : {}", id);
//        return User.builder().id(1L).memo("hello").userName("jasonliu").build();
//        return jdbcUserDao.findUserById(id);
        return mybatisUserDao.findUserById(id);
//        return annotationUserMapper.findUserById(id);
    }

    @Override
    public List<User> findUserByUserName(String userName) {
        return annotationUserMapper.findUserByName(userName);
    }

    @Override
    public User insertUser(User user) {
        annotationUserMapper.insertUser(user);
        return user;
    }
}
