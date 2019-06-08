package com.ljx.chapter6.unit;

import com.ljx.chapter6.enums.SexEnum;
import com.ljx.chapter6.model.User;
import com.ljx.chapter6.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.Constants;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionDefinition;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionSynchronizationTest {

    @Autowired
    private UserServiceImpl userService ;

    @Test
    public void testInsertUserWithTransactionSynchronization() {
        User user = User.builder().userName("test98").note("test98").sex(SexEnum.MALE).build();
        userService.insertUserWithTransactionSynchronization(user);
    }

    @Test(expected = IllegalStateException.class)
    public void testInsertUserWithTransactionSynchronizationWithThrowException() {
        User user = User.builder().userName("test98").note("test98").sex(SexEnum.MALE).build();
        userService.insertUserWithTransactionSynchronizationWithOuterThrowException(user);
    }

    @Test(expected = IllegalStateException.class)
    public void testinsertUserWithTransactionSynchronizationWithInnerThrowException() {
        User user = User.builder().userName("test98").note("test98").sex(SexEnum.MALE).build();
        userService.insertUserWithTransactionSynchronizationWithInnerThrowException(user);
    }

    @Test
    public void testConstants() {
        Constants constants1 = new Constants(TransactionDefinition.class);
        int propagation_required = constants1.asNumber("PROPAGATION_REQUIRED").intValue();
        log.info("######propagation_required:{}",propagation_required);
    }

}
