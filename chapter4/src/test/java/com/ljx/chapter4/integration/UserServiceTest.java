package com.ljx.chapter4.integration;

import com.ljx.chapter4.aspect.introduction.UserServiceParameterValidator;
import com.ljx.chapter4.model.User;
import com.ljx.chapter4.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void insertUserTest() {
        User user = User.builder().id(4L).userName("kele").note("success").build();
        userService.insertUser(user);
    }

    @Test
    public void printUserTest() {
        User user = User.builder().id(4L).userName("kele").note("success").build();
        UserServiceParameterValidator userServiceParameterValidator = (UserServiceParameterValidator) userService;
        if(userServiceParameterValidator.paramterValidator(user)) {
            this.userService.printUser(user);
        }
    }
}
