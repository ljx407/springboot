package com.ljx.chapter6.service.impl;

import com.ljx.chapter6.mapper.UserMapper;
import com.ljx.chapter6.model.User;
import com.ljx.chapter6.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper ;

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,timeout = 1,readOnly = true)
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,timeout = 1)
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int deleteUserById(Long id) {
        return userMapper.deleteUserById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED,timeout = 1)
    public int insertUserWithDefaultPropagatioin(User user) {
        return userMapper.insertUserWithId(user.getId(),user.getUserName(),user.getNote(),user.getSex());
    }

    @Transactional(propagation = Propagation.REQUIRED,timeout = 1)
    public int insertUserWithDefaultPropagationAndThrowException(User user) {
        int result = userMapper.insertUserWithId(user.getId(), user.getUserName(), user.getNote(), user.getSex());
        Assert.isTrue(result == 1 ,"insertUserWithDefaultPropagationAndThrowException error");
        throw new IllegalStateException();
    }

    @Transactional
    public int insertUserWithTransactionSynchronization(User user) {
        int i = userMapper.insertUser(user);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                log.info("#####insertUserWithTransactionSynchronization TransactionSynchronization afterCommit invoke!");
            }

        });
        return i ;
    }

    /**
     * 只有事务提交成功后，才执行事务回调处理函数
     * 该方法验证了外层事务异常时，回调函数不执行
     * @param user
     * @return
     */
    @Transactional
    public int insertUserWithTransactionSynchronizationWithOuterThrowException(User user) {
        userMapper.insertUser(user);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                log.info("#####insertUserWithTransactionSynchronizationWithOuterThrowException TransactionSynchronization afterCommit invoke!");
            }

        });

        throw new IllegalStateException();
    }

    /**
     * 只有事务提交成功后，才执行事务回调处理函数
     * 回调函数异常时并不影响，外层事务
     * @param user
     * @return
     */
    @Transactional
    public int insertUserWithTransactionSynchronizationWithInnerThrowException(User user) {
        int i = userMapper.insertUser(user);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                log.info("#####insertUserWithTransactionSynchronizationWithOuterThrowException TransactionSynchronization afterCommit invoke!");
                throw new IllegalStateException();
            }

        });

        return i;
    }
}
