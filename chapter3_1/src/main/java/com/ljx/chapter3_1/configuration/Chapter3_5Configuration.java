package com.ljx.chapter3_1.configuration;

import com.ljx.chapter3_1.annotion.MyProfile;
import com.ljx.chapter3_1.conditional.MyProfileCondition;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class Chapter3_5Configuration {
        @Autowired
        private Environment environment ;


        @Bean("myProfileDataSourceDev")
        @Conditional(MyProfileCondition.class)
        @MyProfile("dev")
        public DataSource initDataSourceDev() {
                return getDataBase();
        }

        @Bean("myProfileDataSourceTest")
        @Conditional(MyProfileCondition.class)
        @MyProfile("test")
        public DataSource initDataSourceTest() {
                return getDataBase();
        }

        private DataSource getDataBase() {
                Properties properties = new Properties();
                properties.put("driver",environment.getProperty("database.driver"));
                properties.put("url", environment.getProperty("database.url"));
                properties.put("username", environment.getProperty("database.username"));
                properties.put("password", environment.getProperty("database.password"));
                try {
                        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
                        return dataSource;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null ;
        }

}
