package com.ljx.chapter7.configuration;

import com.ljx.chapter7.propertysources.DataSourcePropertySource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties
public class DataSourceConfiguration {

    @Autowired
    private DataSourcePropertySource dataSourcePropertySource ;

    @Bean("dataSource")
    public DataSource initDataSource() {

        Properties properties = new Properties();
        properties.setProperty("jdbcUrl",dataSourcePropertySource.getUrl());
        properties.setProperty("username",dataSourcePropertySource.getUsername());
        properties.setProperty("password",dataSourcePropertySource.getPassword());

        HikariConfig hikariConfig = new HikariConfig(properties);
        hikariConfig.setPoolName("ljx-hikari-datasource");
        hikariConfig.setIdleTimeout(60000);
        hikariConfig.setConnectionTimeout(500);
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setMinimumIdle(2);
        return new HikariDataSource(hikariConfig);

    }
}
