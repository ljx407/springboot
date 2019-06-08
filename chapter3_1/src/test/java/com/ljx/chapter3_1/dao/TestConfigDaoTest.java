package com.ljx.chapter3_1.dao;

import com.ljx.chapter3_1.model.Brand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConfigDaoTest {

    @Autowired
    private TestConfigDao testConfigDao;

    @Test
    public void testFindActiveBrand() {
        Brand one = testConfigDao.findActiveBrand();
        Assert.assertNotNull(one);
        Assert.assertEquals(one.getId(),Long.valueOf(1L));
        Assert.assertEquals(one.getBrandName(),"testB");
        Assert.assertEquals(one.getActive(),"Y");

    }
}
