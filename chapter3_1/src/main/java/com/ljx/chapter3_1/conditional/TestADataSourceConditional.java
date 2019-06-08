package com.ljx.chapter3_1.conditional;

import com.ljx.chapter3_1.dao.TestConfigDao;
import com.ljx.chapter3_1.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

public class TestADataSourceConditional implements Condition {

    @Autowired
    private TestConfigDao testConfigDao ;


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        TestConfigDao testConfigDao = context.getBeanFactory().getBean("testConfigDao", TestConfigDao.class);
        Brand activeBrand = testConfigDao.findActiveBrand();
        return "test".equals(activeBrand.getBrandName());
    }
}
