package com.ljx.chapter9react.dao;

import com.ljx.chapter9react.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao ;

    @Test
    public void test() {
        User j = userDao.findUserByCondition("jasonliu");
        System.out.println(j);

    }
}
