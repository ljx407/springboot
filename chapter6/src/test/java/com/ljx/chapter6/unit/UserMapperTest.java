package com.ljx.chapter6.unit;

import com.ljx.chapter6.enums.SexEnum;
import com.ljx.chapter6.mapper.UserMapper;
import com.ljx.chapter6.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper ;

    @Test
    public void getUserByIdTest() {
        User user = userMapper.getUserById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getNote(),"hellospringboot");
        Assert.assertEquals(user.getUserName(),"jasonliu");
    }

    @Test
    public void findUserByIdTest() {
        User user = userMapper.findUserById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getNote(),"hellospringboot");
        Assert.assertEquals(user.getUserName(),"jasonliu");
    }

    @Test
    @Transactional
    @Rollback
    public void insertUserTest() {
        User user = User.builder().sex(SexEnum.FEMALE).note("test").userName("test").build();
        int result = userMapper.insertUser(user);
        Assert.assertEquals(result,1);
    }
}
