package com.ljx.chapter3_1.configuration;

import com.ljx.chapter3_1.property.BaseDataSource;
import com.ljx.chapter3_1.property.TestAProperties;
import com.ljx.chapter3_1.property.TestBProperties;
import com.ljx.chapter3_1.property.TestConfigProperties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import({TestConfigProperties.class,TestAProperties.class,TestBProperties.class})
@EnableConfigurationProperties
public class Chapter3_4Configuration {

        @Autowired
        private TestConfigProperties testConfigProperties ;

        @Autowired
        private TestAProperties testAProperties;

        @Autowired
        private TestBProperties testBProperties;


        @Bean("testConfigDataSource")
        public DataSource initTestConfigDataSource() {

                try {
                        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(getProperties(testConfigProperties));
                        return dataSource;
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return null ;

        }

        private static Properties getProperties(BaseDataSource testConfigProperties) {
                Properties properties = new Properties();
                properties.put("driver",testConfigProperties.getDriver());
                properties.put("url", testConfigProperties.getUrl());
                properties.put("username", testConfigProperties.getUsername());
                properties.put("password", testConfigProperties.getPassword());
                return properties;
        }

        @Bean("testADataSource")
        public DataSource initTestADataSource() {

                try {
                        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(getProperties(testAProperties));
                        return dataSource;
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return null ;
        }

        @Bean("testBDataSource")
        public DataSource initTestBDataSource() {

                try {
                        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(getProperties(testBProperties));
                        return dataSource;
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return null ;
        }
}
