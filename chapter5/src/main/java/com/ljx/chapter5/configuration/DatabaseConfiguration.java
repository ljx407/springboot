package com.ljx.chapter5.configuration;

import com.ljx.chapter5.typehandler.BookTypeTypeHandler;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    public DataSource initDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/chapter5?useSSL=false");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        return new HikariDataSource(hikariConfig);

    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory initSqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(initDataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.ljx.chapter5.model");
        sqlSessionFactoryBean.setMapperLocations(new Resource[]{new ClassPathResource("mapper/BookMapper.xml")});
        sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new BookTypeTypeHandler()});
        //        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        org.apache.ibatis.session.Configuration configuration =  new org.apache.ibatis.session.Configuration();
//        TransactionFactory transactionFactory = new JdbcTransactionFactory();
//        Environment environment = new Environment("develop",transactionFactory,initDataSource());
//        configuration.setEnvironment(environment);
//        configuration.getTypeHandlerRegistry().register(BookTypeTypeHandler.class);
//        return sqlSessionFactoryBuilder.build(configuration);
        try {
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }
}
