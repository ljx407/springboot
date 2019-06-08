package com.ljx.chapter5.unit;

import com.ljx.chapter5.configuration.Chapter5MybatisConfiguraion;
import com.ljx.chapter5.dao.RoleDao;
import com.ljx.chapter5.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
public class Chapter5MybatisConfigurationTest {

    private AnnotationConfigApplicationContext annotationConfigApplicationContext ;

    @Before
    public void before() {
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Chapter5MybatisConfiguraion.class);
    }

    @Test
    public void testRoleDao() {
        RoleDao roleDao = annotationConfigApplicationContext.getBean("roleDao", RoleDao.class);
        Role role = roleDao.findById(1);
        Assert.assertNotNull(role);
        Assert.assertEquals(role.getName(),"admin");

    }
}
