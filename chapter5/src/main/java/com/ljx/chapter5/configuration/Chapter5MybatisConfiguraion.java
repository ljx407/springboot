package com.ljx.chapter5.configuration;

import com.ljx.chapter5.dao.RoleDao;
import com.ljx.chapter5.dao.RoleDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class Chapter5MybatisConfiguraion {

    @Bean
    public SqlSessionFactory initSqlSessionFactory() {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
            return build;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Bean("roleDao")
    public RoleDao initRoleDao() {
        RoleDao roleDao = new RoleDaoImpl(initSqlSessionFactory());
        return roleDao;
    }
}
