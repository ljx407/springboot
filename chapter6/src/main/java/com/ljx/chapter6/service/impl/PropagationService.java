package com.ljx.chapter6.service.impl;

import com.ljx.chapter6.model.Role;
import com.ljx.chapter6.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Service
public class PropagationService {
    @Autowired
    private RoleServiceImpl roleService ;

    @Autowired
    private UserServiceImpl userService ;

    @Transactional
    public int insertRole(Role role) {
        int result = roleService.insertRoleWithDefaultPropagatoin(role);
        return result ;
    }

    @Transactional
    public int insertUserAndRoleWithThrowException(User user, Role role) {
        int result1 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result1 == 1,"userService.insertUserWithDefaultPropagatioin 插入失败");
        int result = roleService.insertRoleWithDefaultPropagatoin(role);
        Assert.isTrue(result == 1,"roleService.insertRoleWithDefaultPropagatoin 插入失败");
        throw new IllegalStateException();
    }

    // 验证propagation.required
    // 当子方法设定为required时，若外层存在事务，则公用一个事务
    // 不管外层事务发生异常或，子方法事务发生异常都会导致整个事务回滚
    // 当前方法验证，若子方法发生异常，外层事务本应该回滚，但是在代码层次手动捕获异常，导致外层事务正常结束的情况
    // 从代码上故意破坏事务的传播一致性
    // 在这种情况下，spring已把该事务，标志为一个需要回滚的事务，若不抛出异常让事务回滚，spring自动会抛出UnexpectedRollbackException
    // org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only
    // 原因在于事务的开始在外层，里面发生异常时，执行rollback指令实在外层结束阶段，spring表级外层事务为rollback事务，但最后外层事务确不执行rollback指令所以会冲突
    @Transactional
    public int insertUserAndRoleWithRequiredAndThrowException(User user,Role role) {
        int result1 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result1 == 1,"roleService.insertUserWithDefaultPropagatioin 插入失败");

        try {
            int result = roleService.insertRoleWithDefaultPropagatoinWithException(role);
            Assert.isTrue(result == 1, "roleService.insertRoleWithDefaultPropagatoinWithException 插入失败");
        } catch (IllegalStateException e) {
            log.error("######" + e.getMessage(),e);
        }
        return result1;
    }

    // 验证propagation.required_new
    // 当子方法设定为required_new时，会把当前事务挂起，重新获取一个连接，执行sql
    // 不管外层事务是否回滚，都不影响子方法中的事务
    // 当前方法测试外层抛出异常不影响子类方法事务
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithNew(User user, Role role) {
        int result = roleService.insertRoleWithPropagatoinNew(role);
        int result2 = userService.insertUserWithDefaultPropagationAndThrowException(user);
        return result + result2 ;
    }

    // 验证propagation.required_new
    // 当子方法设定为required_new时,由于mysql不支持嵌套行事务，只支持扁平话事务，所以会把当前事务挂起，重新获取一个连接，执行sql
    // 不管外层事务是否回滚，都不影响子方法中的事务
    // 当前方法测试子方法事务抛出异常不影响外层方法执行
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithNew1(User user, Role role) {
        int result2 = userService.insertUser(user);
        try {
            roleService.insertRoleWithPropagatoinNewAndThrowException(role);
        } catch (IllegalStateException e) {
            log.error("#########" + e.getMessage(),e);
        }
        return result2 ;
    }

    // 验证propagation.required_new
    // 当子方法设定为required_new时,由于mysql不支持嵌套行事务，只支持扁平话事务，所以会把当前事务挂起，重新获取一个连接，执行sql
    // 不管外层事务是否回滚，都不影响子方法中的事务
    // 当前方法测试子方法抛出异常不捕获，spring会不会检测事务不一致性
    // 通过测试可知，spring并不会检测requried__new子事务中抛出的异常，在外层是否应该被捕获或抛出
    // 因为spring也不知道你是否想要抛出子事务的异常去影响外层事务
    // 如果你想影响外层事务，则不要捕获异常让异常抛到外层，外层事务检测到异常自动回回滚
    // 如果你不想影响外层事务，则需要捕获子事务异常，自己处理掉，不要抛出到外层 就如insertUserAndRoleWithNew1测试用例所示
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithNew2(User user, Role role) {
        int result2 = userService.insertUser(user);
        roleService.insertRoleWithPropagatoinNewAndThrowException(role);
        return result2 ;
    }

    // 验证propagation.required_nested
    // 当子方法设定为required_nested时，会开启一个savepoint，然后在执行sql
    // 如果子方法发生异常，则子方法会回滚，但不影响外层事务的提交
    // 但是如果外层事务发生异常，则子方法同样会回滚
    // 还要注意的一点是，并不是所有数据库都支持savepoint，如不支持savepoint直接等同与required_new
    // 当前方法测试子方法子方法发生异常不影响外层事务提交
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithNested(User user, Role role) {
        int result2 = userService.insertUser(user);
        try {
            int result = roleService.insertRoleWithPropagatoinNestedAndThrowException(role);
        } catch (IllegalStateException e) {
            log.error(e.getMessage(),e);
        }
        return result2 ;
    }

    // 验证propagation.required_nested
    // 当子方法设定为required_nested时，会开启一个savepoint，然后在执行sql
    // 如果子方法发生异常，则子方法会回滚，但不影响外层事务的提交
    // 但是如果外层事务发生异常，则子方法同样会回滚
    // 还要注意的一点是，并不是所有数据库都支持savepoint，如不支持savepoint直接等同与required_new
    // 当前方法测试子外层事务发生异常导致子方法事务回滚
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithNested1(User user, Role role) {

        int result = roleService.insertRoleWithPropagatoinNested(role);
        Assert.isTrue(result == 1, "insertRoleWithPropagatoinNested 发生错误");
        int result2 = userService.insertUserWithDefaultPropagationAndThrowException(user);
        return result2 ;
    }

    // 验证propagation.support
    // 当子方法设定为support时，若外层有事务，则加入，否则spring不管里该方法的事务，使用数据库默认事务
    // 数据库默认事务，为自动提交，即每个修改都会开启一个新事务，执行完后马上提交
    // 若外层存在事务，行为等同与required
    // 即外层事务发生异常或子方法事务发生异常都会导致事务回滚
    // 该方法验证在外层存在事务的前提下，外层事务发生异常导致整个事务回滚
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithSupport(User user, Role role) {

        int result = roleService.insertRoleWithPropagatoinSupports(role);
        Assert.isTrue(result == 1, "insertRoleWithPropagatoinSupports 发生错误");
        int result2 = userService.insertUserWithDefaultPropagationAndThrowException(user);
        return result2 ;
    }

    // 验证propagation.support
    // 当子方法设定为support时，若外层有事务，则加入，否则spring不管里该方法的事务，使用数据库默认事务
    // 数据库默认事务，为自动提交，即每个修改都会开启一个新事务，执行完后马上提交
    // 若外层存在事务，行为等同与required
    // 即外层事务发生异常或子方法事务发生异常都会导致事务回滚
    // 该方法验证在外层存在事务的前提下，子事务发生异常导致整个事务回滚
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithSupport1(User user, Role role) {

        int result2 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result2 == 1, "insertUserWithDefaultPropagatioin 发生错误");
        roleService.insertRoleWithPropagatoinSupportsWithThrowException(role);

        return result2 ;
    }

    // 验证propagation.support
    // 当子方法设定为support时，若外层有事务，则加入，否则spring不管里该方法的事务，使用数据库默认事务
    // 数据库默认事务，为自动提交，即每个修改都会开启一个新事务，执行完后马上提交
    // 若外层存在事务，行为等同与required
    // 即外层事务发生异常或子方法事务发生异常都会导致事务回滚
    // 该方法验证在外层存在事务的前提下，子事务发生异常导致整个事务回滚
    // 注意此时一定要把异常抛出到外层，让整个事务回滚，否则也会出现用例insertUserAndRoleWithRequiredAndThrowException的情况
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithSupport2(User user, Role role) {

        int result2 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result2 == 1, "insertUserWithDefaultPropagatioin 发生错误");
        try {
            roleService.insertRoleWithPropagatoinSupportsWithThrowException(role);
        } catch (IllegalStateException e) {
            log.error(e.getMessage(),e);

        }
        return result2 ;
    }


    // 验证propagation.support
    // 当子方法设定为support时，若外层有事务，则加入，否则spring不管里该方法的事务，使用数据库默认事务
    // 数据库默认事务，为自动提交，即每个修改都会开启一个新事务，执行完后马上提交
    // 若外层不存在事务，使用默认事务
    // 该方法验证在外层不存在事务的前提下，子事务发生异常不影响外层事务
    // 甚至子事务自己抛出异常，也不影响已插入的数据
    public int insertUserAndRoleWithSupport3(User user, Role role) {

        int result2 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result2 == 1, "insertUserWithDefaultPropagatioin 发生错误");

        roleService.insertRoleWithPropagatoinSupportsWithThrowException(role);

        return result2 ;
    }

    // 验证propagation.support
    // 当子方法设定为support时，若外层有事务，则加入，否则spring不管里该方法的事务，使用数据库默认事务
    // 数据库默认事务，为自动提交，即每个修改都会开启一个新事务，执行完后马上提交
    // 若外层不存在事务，使用默认事务
    // 该方法验证在外层不存在事务的前提下，外层事务发生异常不影响子事务
    public int insertUserAndRoleWithSupport4(User user, Role role) {

        int result = roleService.insertRoleWithPropagatoinSupports(role);
        Assert.isTrue(result == 1, "insertRoleWithPropagatoinSupports 发生错误");

        int result2 = userService.insertUserWithDefaultPropagationAndThrowException(user);

        return result2 ;
    }

    // 验证propagation.not_support
    // 当前事务为not_support，不管外层是否存在事务，子方法事务采用数据库默认事务
    // 该方法验证在外层存在事务的前提下，外层事务发生异常不影响子事务
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithNotSupport(User user, Role role) {

        int result = roleService.insertRoleWithPropagatoinNotSupports(role);
        Assert.isTrue(result == 1, "insertRoleWithPropagatoinNotSupports 发生错误");

        int result2 = userService.insertUserWithDefaultPropagationAndThrowException(user);

        return result2 ;
    }

    // 验证propagation.not_support
    // 当前事务为not_support，不管外层是否存在事务，子方法事务采用数据库默认事务
    // 该方法验证在外层不存在事务的前提下，子事务发生异常不影响外层事务
    // 甚至子事务本身发生异常，数据依然会插入至数据库中
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithNotSupport1(User user, Role role) {

        int result2 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result2 == 1, "insertRoleWithPropagatoinNotSupports 发生错误");

        try {
            int result = roleService.insertRoleWithPropagatoinNotSupportsAndThrowException(role);
        } catch (IllegalStateException e) {
            log.error(e.getMessage(),e);
        }

        return result2 ;
    }

    // 验证propagation.not_support
    // 当前事务为not_support，不管外层是否存在事务，子方法事务采用数据库默认事务
    // 该方法验证在外层不存在事务的前提下，外层事务发生异常不影响子事务
    public int insertUserAndRoleWithNotSupport2(User user, Role role) {

        int result = roleService.insertRoleWithPropagatoinNotSupports(role);
        Assert.isTrue(result == 1, "insertRoleWithPropagatoinNotSupports 发生错误");

        int result2 = userService.insertUserWithDefaultPropagationAndThrowException(user);

        return result2 ;
    }

    // 验证propagation.not_support
    // 当前事务为not_support，不管外层是否存在事务，子方法事务采用数据库默认事务
    // 该方法验证在外层不存在事务的前提下，子事务发生异常不影响外层事务
    // 甚至子事务本身发生异常，数据依然会插入至数据库中
    public int insertUserAndRoleWithNotSupport3(User user, Role role) {

        int result2 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result2 == 1, "insertRoleWithPropagatoinNotSupports 发生错误");

        try {
            int result = roleService.insertRoleWithPropagatoinNotSupportsAndThrowException(role);
        } catch (IllegalStateException e) {
            log.error(e.getMessage(),e);
        }

        return result2 ;
    }

    // 验证propagation.mandatory
    // 当前事务为mandatory，外层必须存在事务，否则报错
    // 该方法检测外层存在事务,作用等同于required，加入已存在的事务中
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithMandatory(User user, Role role) {

        int result2 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result2 == 1, "insertUserWithDefaultPropagatioin 插入异常");

        int result = roleService.insertRoleWithPropagatoinMandatory(role);
        Assert.isTrue(result == 1, "insertRoleWithPropagatoinMandatory 插入异常");


        return result + result2 ;
    }

    // 验证propagation.mandatory
    // 当前事务为mandatory，外层必须存在事务，否则报错
    // 该方法检测外层存在事务,作用等同于required，加入已存在的事务中
    // 若外层事务发生异常，子事务回滚
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithMandatoryAndThrowException(User user, Role role) {

        int result = roleService.insertRoleWithPropagatoinMandatory(role);
        Assert.isTrue(result == 1, "insertRoleWithPropagatoinMandatory 插入异常");

        int result2 = userService.insertUserWithDefaultPropagationAndThrowException(user);

        return result + result2 ;
    }

    // 验证propagation.mandatory
    // 当前事务为mandatory，外层必须存在事务，否则报错
    // 该方法检测外层存在事务,作用等同于required，加入已存在的事务中
    // 若子事务发生异常，外层同样回滚
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUserAndRoleWithMandatoryAndThrowException1(User user, Role role) {

        int result2 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result2 == 1, "insertUserWithDefaultPropagatioin 插入异常");
        int result = roleService.insertRoleWithPropagatoinMandatoryAndThrowException(role);

        return result + result2 ;
    }

    // 验证propagation.mandatory
    // 当前事务为mandatory，外层必须存在事务，否则报错
    // 该方法检测外层不存在事务,spring会检测并抛出异常
    // org.springframework.transaction.IllegalTransactionStateException: No existing transaction found for transaction marked with propagation 'mandatory'
    public int insertUserAndRoleWithMandatoryAndThrowException2(User user, Role role) {

        int result = roleService.insertRoleWithPropagatoinMandatory(role);

        int result2 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result2 == 1, "insertUserWithDefaultPropagatioin 插入异常");

        return result + result2 ;
    }

    // 验证propagation.never
    // 当前事务为never，外层必须不能存在事务，否则报错，当外层不存在事务时，采用数据库默认事务
    // 该方法检测外层存在事务,spring会检测并抛出异常
    // org.springframework.transaction.IllegalTransactionStateException: No existing transaction found for transaction marked with propagation 'mandatory'
    @Transactional
    public int insertUserAndRoleWithNever(User user, Role role) {

        int result = roleService.insertRoleWithPropagatoinNever(role);

        int result2 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result2 == 1, "insertUserWithDefaultPropagatioin 插入异常");

        return result + result2 ;
    }

    // 验证propagation.never
    // 当前事务为never，外层必须不能存在事务，否则报错，当外层不存在事务时，采用数据库默认事务
    // 若外层不存在异常，等同于not_support
    // 本方法验证，外层发生异常，对子事务无任何影响
    public int insertUserAndRoleWithNever1(User user, Role role) {

        int result = roleService.insertRoleWithPropagatoinNever(role);
        Assert.isTrue(result == 1, "insertRoleWithPropagatoinNever 插入异常");

        int result2 = userService.insertUserWithDefaultPropagationAndThrowException(user);

        return result + result2 ;
    }

    // 验证propagation.never
    // 当前事务为never，外层必须不能存在事务，否则报错，当外层不存在事务时，采用数据库默认事务
    // 若外层不存在异常，等同于not_support
    // 本方法验证，本事务发生异常，对外层事务无任何影响
    // 甚至，本事务本身的修改也能提交到数据库
    public int insertUserAndRoleWithNever2(User user, Role role) {

        int result2 = userService.insertUserWithDefaultPropagatioin(user);
        Assert.isTrue(result2 == 1, "insertUserWithDefaultPropagatioin 插入异常");

        int result = roleService.insertRoleWithPropagatoinNeverAndThrowException(role);


        return result + result2 ;
    }
}
