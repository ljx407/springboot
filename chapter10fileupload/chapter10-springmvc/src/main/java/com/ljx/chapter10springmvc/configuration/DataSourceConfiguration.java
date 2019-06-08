package com.ljx.chapter10springmvc.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfiguration {

    @Value("${datasource.url}")
    private String url ;
    @Value("${datasource.username}")
    private String username ;
    @Value("${datasource.password}")
    private String password ;

    @Bean("datasource")
    public DataSource initDataSource() {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setPoolName("chapter10springmvc");
//        hikariConfig.setJdbcUrl(url);
//        hikariConfig.setUsername(username);
//        hikariConfig.setPassword(password);
//        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
//        return hikariDataSource;
//        Properties properties = new Properties();
//        properties.setProperty("url",url);
//        properties.setProperty("username",username);
//        properties.setProperty("password",password);
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return basicDataSource;
//        BasicDataSourceFactory.createDataSource(properties);
    }

    @Bean
    public DataSourceTransactionManager initDataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(initDataSource());
        return dataSourceTransactionManager;
    }
}
