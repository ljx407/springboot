package com.ljx.chapter3_1.configuration;

import com.ljx.chapter3_1.model.ScopeBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Chapter3_6Configuration {

    @Bean("scopeBeanWithPrototype")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ScopeBean initScopeBean() {
        return new ScopeBean();
    }

    @Bean("scopeBeanWithSingleton")
    public ScopeBean initScopeBeanWitSingleton() {
        return new ScopeBean();
    }

}
