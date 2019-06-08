package com.ljx.chapter6.service.impl;

import com.ljx.chapter6.mapper.RoleMapper;
import com.ljx.chapter6.model.Role;
import com.ljx.chapter6.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    private AtomicInteger count = new AtomicInteger(1);

    @Override
    public Role findById(Long id) {
        return roleMapper.findById(id);
    }

    @Override
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }

    @Transactional
    @Override
    public int deleteRoleById(Long id) {
        return roleMapper.deleteRoleById(id);
    }

    // 该方法可以验证事务子调用失效
    // 理论上如果，我插入两条roles，第一条会成功并且保存至数据库
    // 第二条会失败，不会保存至数据库
    // 实际上子调用，insertRoleWithPropagatoinNewThrowExceptionForSpeicifyCount上的reqired_new不会启作用
    // insertRoleWithPropagatoinNewThrowExceptionForSpeicifyCount使用的是当前事务
    // 所以只要有一条发生错误整个事务回滚
    @Override
    @Transactional
    public int batchInsertRole(List<Role> roleList) {
        for (Role role : roleList) {
            insertRoleWithPropagatoinNewThrowExceptionForSpeicifyCount(role,2);
        }
        return 0 ;
    }

    @Transactional
    public int insertRoleWithDefaultPropagatoin(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        return result;
    }

    @Transactional
    public int insertRoleWithDefaultPropagatoinWithException(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        throw new IllegalStateException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int insertRoleWithPropagatoinNew(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int insertRoleWithPropagatoinNewThrowExceptionForSpeicifyCount(Role role,int throwExepectCount) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");

        if(count.getAndIncrement() == throwExepectCount) {
            count.set(1);
            throw new IllegalStateException();
        }
        return result;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int insertRoleWithPropagatoinNewAndThrowException(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        throw new IllegalStateException();
    }

    @Transactional(propagation = Propagation.NESTED)
    public int insertRoleWithPropagatoinNested(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        return result ;
    }

    @Transactional(propagation = Propagation.NESTED)
    public int insertRoleWithPropagatoinNestedAndThrowException(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        throw new IllegalStateException();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public int insertRoleWithPropagatoinSupports(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        return result ;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public int insertRoleWithPropagatoinSupportsWithThrowException(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        throw new IllegalStateException() ;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int insertRoleWithPropagatoinNotSupports(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        return  result ;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int insertRoleWithPropagatoinNotSupportsAndThrowException(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        throw new IllegalStateException() ;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public int insertRoleWithPropagatoinMandatory(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public int insertRoleWithPropagatoinMandatoryAndThrowException(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        throw new IllegalStateException();
    }

    @Transactional(propagation = Propagation.NEVER)
    public int insertRoleWithPropagatoinNever(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        return result;
    }

    @Transactional(propagation = Propagation.NEVER)
    public int insertRoleWithPropagatoinNeverAndThrowException(Role role) {
        int result = roleMapper.insertRoleWithIdAndName(role.getId(),role.getRoleName());
        Assert.isTrue(result == 1 ,"插入失败");
        throw new IllegalStateException();
    }
}
