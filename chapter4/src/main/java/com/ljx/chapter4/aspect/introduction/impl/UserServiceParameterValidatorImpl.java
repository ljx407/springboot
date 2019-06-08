package com.ljx.chapter4.aspect.introduction.impl;

import com.ljx.chapter4.aspect.introduction.UserServiceParameterValidator;
import com.ljx.chapter4.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceParameterValidatorImpl implements UserServiceParameterValidator {

    @Override
    public boolean paramterValidator(User user) {
        log.info("###### introduction UserServiceParameterValidatorImpl .......");
        return user != null;
    }
}
