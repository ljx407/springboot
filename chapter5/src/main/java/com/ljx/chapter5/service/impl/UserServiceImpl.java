package com.ljx.chapter5.service.impl;

import com.ljx.chapter5.dao.UserDao;
import com.ljx.chapter5.model.User;
import com.ljx.chapter5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> findUsers(String username, String note) {
        return null;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public int delete(User user) {
        return 0;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return null;
    }
}
