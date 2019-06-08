package com.ljx.chapter7.factorybeans;

import com.ljx.chapter7.service.UserService;
import com.ljx.chapter7.service.impl.UserServiceAdviceImpl;
import org.springframework.beans.factory.FactoryBean;

public class UserServiceFactoryBean implements FactoryBean<UserService> {

    @Override
    public UserService getObject() throws Exception {
        return new UserServiceAdviceImpl(-99L);
    }

    @Override
    public Class<?> getObjectType() {
        return UserService.class;
    }
}
