package com.ljx.chapter6.unit;

import com.ljx.chapter6.mapper.RoleMapper;
import com.ljx.chapter6.model.Role;
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
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper ;

    @Test
    public void findByIdTest() {
        Role role = roleMapper.findById(1L);
        Assert.assertNotNull(role);
        Assert.assertEquals(role.getRoleName(),"管理员");
    }

    @Test
    @Transactional
    @Rollback
    public void insertRoleTest() {
        Role role = Role.builder().roleName("admin").build();
        int result = roleMapper.insertRole(role);
        Assert.assertEquals(result,1);

    }
}
