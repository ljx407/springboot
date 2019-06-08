package com.ljx.chapter6.unit;

import com.ljx.chapter6.model.Role;
import com.ljx.chapter6.service.RoleService;
import com.ljx.chapter6.service.impl.RoleBatchServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleBatchServiceTest {

    @Autowired
    private RoleBatchServiceImpl roleBatchService ;

    @Autowired
    private RoleService roleService ;

    @Test
    public void test() {
        List<Role> list = new ArrayList<>();
        list.add(Role.builder().id(100L).roleName("test1").build());
        list.add(Role.builder().id(101L).roleName("test2").build());
        try {
            roleBatchService.batchInsert(list);
        } catch (IllegalStateException e) {
            Role role1 = roleService.findById(100L);
            Assert.assertNotNull(role1);

            int result1 = roleService.deleteRoleById(100L);
            Assert.assertEquals(result1,1);

            Role role2 = roleService.findById(101L);
            Assert.assertNull(role2);
        }
    }
}
