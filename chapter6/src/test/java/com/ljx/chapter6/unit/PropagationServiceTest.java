package com.ljx.chapter6.unit;

import com.ljx.chapter6.enums.SexEnum;
import com.ljx.chapter6.model.Role;
import com.ljx.chapter6.model.User;
import com.ljx.chapter6.service.impl.PropagationService;
import com.ljx.chapter6.service.impl.RoleServiceImpl;
import com.ljx.chapter6.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagationServiceTest {

    @Autowired
    private UserServiceImpl userService ;

    @Autowired
    private RoleServiceImpl roleService ;

    @Autowired
    private PropagationService propagationService;

    private ThreadLocal<User> userThreadLocal ;

    private ThreadLocal<Role> roleThreadLocal;

    @Before
    public void before() {
        userThreadLocal = new ThreadLocal<>();
        userThreadLocal.set(User.builder().id(99L).note("propagationUser").sex(SexEnum.MALE).userName("propagationUser").build());

        roleThreadLocal = new ThreadLocal<>();
        roleThreadLocal.set(Role.builder().id(99L).roleName("propagationRole").build());
    }

    @Test
    public void testWithDefaultPropagation() {
        int result = propagationService.insertRole(roleThreadLocal.get());
        Assert.assertEquals(result,1);

        validRoleExistAndDelete99();

    }

    @Test
    public void testWithDefaultPropagationAndThrowException() {
        try {
            propagationService.insertUserAndRoleWithThrowException(userThreadLocal.get(),roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validUserAndRoleNotExist();
        }

    }

    @Test(expected = UnexpectedRollbackException.class)
    public void testWithDefaultPropagationAndThrowException1() {
        int result = propagationService.insertUserAndRoleWithRequiredAndThrowException(userThreadLocal.get(),roleThreadLocal.get());
        Assert.assertEquals(result,1);

        // 验证代码不会执行到该行
        throw new UnsupportedOperationException();

    }


    @Test
    public void testWithPropagationNew() {
        try {
            propagationService.insertUserAndRoleWithNew(userThreadLocal.get(),roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validRoleExistAndDelete99();

        }

    }

    @Test
    public void testWithPropagationNew1() {

        int result = propagationService.insertUserAndRoleWithNew1(userThreadLocal.get(), roleThreadLocal.get());
        Assert.assertEquals(result,1);

        int result2 = userService.deleteUserById(userThreadLocal.get().getId());
        Assert.assertEquals(result2,1);

    }

    @Test
    public void testWithPropagationNew2() {

        try {
            int result = propagationService.insertUserAndRoleWithNew2(userThreadLocal.get(), roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validUserNotExist();
        }

    }

    @Test
    public void testWithPropagationNested() {

        int result = propagationService.insertUserAndRoleWithNested(userThreadLocal.get(), roleThreadLocal.get());
        Assert.assertEquals(result,1);

        validUserExistAndDelete99();

    }

    @Test
    public void testWithPropagationNested1() {

        try {
            int result = propagationService.insertUserAndRoleWithNested1(userThreadLocal.get(), roleThreadLocal.get());
            Assert.assertEquals(result, 1);
        } catch (IllegalStateException e) {
            validUserAndRoleNotExist();
        }

    }

    @Test
    public void testWithPropagationSupport() {

        try {
            int result = propagationService.insertUserAndRoleWithSupport(userThreadLocal.get(), roleThreadLocal.get());
            Assert.assertEquals(result, 1);
        } catch (IllegalStateException e) {
            validUserAndRoleNotExist();
        }

    }

    @Test
    public void testWithPropagationSupport1() {

        try {
            int result = propagationService.insertUserAndRoleWithSupport1(userThreadLocal.get(), roleThreadLocal.get());
            Assert.assertEquals(result, 1);

        } catch (IllegalStateException e) {
            validUserAndRoleNotExist();
        }

    }

    @Test(expected = UnexpectedRollbackException.class)
    public void testWithPropagationSupport2() {

        int result = propagationService.insertUserAndRoleWithSupport2(userThreadLocal.get(), roleThreadLocal.get());

        // 检测不会执行到该行
        throw new UnsupportedOperationException();

    }

    @Test
    public void testWithPropagationSupport3() {

        try {
            propagationService.insertUserAndRoleWithSupport3(userThreadLocal.get(), roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validUserAndRoleExistAndDeleteBoth();
        }


    }

    @Test
    public void testWithPropagationSupport4() {

        try {
            propagationService.insertUserAndRoleWithSupport4(userThreadLocal.get(), roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validUserNotExist();

            validRoleExistAndDelete99();
        }

    }

    @Test
    public void testWithPropagationNotSupport() {

        try {
            propagationService.insertUserAndRoleWithNotSupport(userThreadLocal.get(), roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validUserNotExist();

            validRoleExistAndDelete99();
        }

    }

    @Test
    public void testWithPropagationNotSupport1() {

        int result = propagationService.insertUserAndRoleWithNotSupport1(userThreadLocal.get(), roleThreadLocal.get());
        Assert.assertEquals(result,1);

        validUserAndRoleExistAndDeleteBoth();


    }

    @Test
    public void testWithPropagationNotSupport2() {

        try {
            propagationService.insertUserAndRoleWithNotSupport2(userThreadLocal.get(), roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validUserNotExist();

            validRoleExistAndDelete99();
        }

    }

    @Test
    public void testWithPropagationNotSupport3() {

        int result = propagationService.insertUserAndRoleWithNotSupport3(userThreadLocal.get(), roleThreadLocal.get());
        Assert.assertEquals(result,1);

        validUserAndRoleExistAndDeleteBoth();

    }

    @Test
    public void testWithPropagationMandatory() {

        int result = propagationService.insertUserAndRoleWithMandatory(userThreadLocal.get(), roleThreadLocal.get());
        Assert.assertEquals(result,2);

        validUserAndRoleExistAndDeleteBoth();

    }

    @Test
    public void testWithPropagationMandatoryAndThrowException() {

        try {
            int result = propagationService.insertUserAndRoleWithMandatoryAndThrowException(userThreadLocal.get(), roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validUserAndRoleNotExist();
        }

    }

    @Test
    public void testWithPropagationMandatoryAndThrowException1() {

        try {
            int result = propagationService.insertUserAndRoleWithMandatoryAndThrowException1(userThreadLocal.get(), roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validUserAndRoleNotExist();
        }

    }

    @Test(expected = IllegalTransactionStateException.class)
    public void testWithPropagationMandatoryAndThrowException2() {

        propagationService.insertUserAndRoleWithMandatoryAndThrowException2(userThreadLocal.get(), roleThreadLocal.get());

        // 验证不会执行到该行
        throw new UnsupportedOperationException();

    }

    @Test(expected = IllegalTransactionStateException.class)
    public void testWithPropagationNever() {

        propagationService.insertUserAndRoleWithNever(userThreadLocal.get(), roleThreadLocal.get());

        // 验证不会执行到该行
        throw new UnsupportedOperationException();

    }

    @Test
    public void testWithPropagationNever1() {

        try {
            propagationService.insertUserAndRoleWithNever1(userThreadLocal.get(), roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validUserNotExist();
            validRoleExistAndDelete99();
        }

    }


    @Test
    public void testWithPropagationNever2() {

        try {
            propagationService.insertUserAndRoleWithNever2(userThreadLocal.get(), roleThreadLocal.get());
        } catch (IllegalStateException e) {
            validUserAndRoleExistAndDeleteBoth();
        }

    }

    @Test
    public void testSelfInvokeTransactionInvalid() {
        List<Role> list = new ArrayList<>();
        list.add(Role.builder().id(100L).roleName("test1").build());
        list.add(Role.builder().id(101L).roleName("test2").build());
        try {
            roleService.batchInsertRole(list);
        } catch (IllegalStateException e) {
            Role role1 = roleService.findById(100L);
            Assert.assertNull(role1);

            Role role2 = roleService.findById(101L);
            Assert.assertNull(role2);
        }
    }


    private void validUser(boolean exist) {
        User user = userService.findUserById(userThreadLocal.get().getId());
        if(exist) {
            Assert.assertNotNull(user);
        } else {
            Assert.assertNull(user);
        }
    }

    private void validRole(boolean exist)  {
        Role role = roleService.findById(roleThreadLocal.get().getId());
        if(exist) {
            Assert.assertNotNull(role);
        } else {
            Assert.assertNull(role);
        }
    }

    private void validUserExistAndDelete99()  {
        validUser(true);
        deleteUser99();
    }

    private void validUserNotExist()  {
        validUser(false);
    }

    private void validRoleNotExist()  {
        validRole(false);
    }

    private void validRoleExistAndDelete99()  {
        validRole(true);
        deleteRole99();
    }

    private void validUserAndRoleExistAndDeleteBoth() {
        validUserExistAndDelete99();
        validRoleExistAndDelete99();
    }

    private void validUserAndRoleNotExist() {
        validUserNotExist();
        validRoleNotExist();
    }

    private void deleteUser99() {
        int result = userService.deleteUserById(userThreadLocal.get().getId());
        Assert.assertEquals(result,1);
    }

    private void deleteRole99() {
        int result1 = roleService.deleteRoleById(roleThreadLocal.get().getId());
        Assert.assertEquals(result1,1);
    }
}
