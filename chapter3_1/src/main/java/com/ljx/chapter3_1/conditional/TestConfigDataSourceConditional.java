package com.ljx.chapter3_1.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class TestConfigDataSourceConditional implements Condition {


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();

        return !StringUtils.isEmpty(environment.getProperty("database.testconfig.driver"))
        && !StringUtils.isEmpty(environment.getProperty("database.testconfig.url"))
        && !StringUtils.isEmpty(environment.getProperty("database.testconfig.username"))
        && !StringUtils.isEmpty(environment.getProperty("database.testconfig.password"));
    }
}
