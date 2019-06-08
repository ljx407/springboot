package com.ljx.chapter3_1.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:application.properties"})
public class Chapter3_7Configuration {

    @Autowired
    private Environment environment ;

    @Bean("testDevProfileDataSource")
    @Profile("dev")
    public DataSource initDataSource() throws Exception {

        Properties properties = new Properties();
        properties.put("driver", environment.getProperty("database.driver"));
        properties.put("url",environment.getProperty("database.url"));
        properties.put("username",environment.getProperty("database.username"));
        properties.put("password",environment.getProperty("database.password"));

        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        return dataSource ;

    }

    @Bean("testProfileDataSource")
    public DataSource initDataSourcA() throws Exception {

        Properties properties = new Properties();
        properties.put("driver", "com.mysql.jdbc.Driver");
        properties.put("url","jdbc:mysql://127.0.0.1:3306/test?useSSL=false");
        properties.put("username","root");
        properties.put("password","root");

        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        return dataSource ;

    }

}
