package com.ljx.chapter3_1.conditional;

import com.ljx.chapter3_1.dao.TestConfigDao;
import com.ljx.chapter3_1.model.Brand;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class TestBDataSourceConditional implements Condition {


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        TestConfigDao testConfigDao = context.getBeanFactory().getBean("testConfigDao", TestConfigDao.class);
        Brand activeBrand = testConfigDao.findActiveBrand();
        return "testB".equals(activeBrand.getBrandName());
    }
}
