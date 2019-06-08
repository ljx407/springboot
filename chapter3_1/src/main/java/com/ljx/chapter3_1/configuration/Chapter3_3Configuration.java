package com.ljx.chapter3_1.configuration;

import com.ljx.chapter3_1.Chapter31Application;
import com.ljx.chapter3_1.property.DataSourcePropertisSourceWithConfigurationProperties;
import com.ljx.chapter3_1.property.DatasourceProperties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import(Chapter3_4Configuration.class)
@ComponentScan(
        basePackages = {"com.ljx.chapter3_1"}
        ,excludeFilters = {
                @ComponentScan.Filter(type= FilterType.REGEX,pattern = {"com.ljx.chapter3_1.configuration.*"})
                ,@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,classes = Chapter31Application.class)
        }
)
@EnableConfigurationProperties(DataSourcePropertisSourceWithConfigurationProperties.class)
public class Chapter3_3Configuration {

        @Autowired
        private Environment environment ;

        @Autowired
        private DatasourceProperties datasourceProperties ;

        @Autowired
        private DataSourcePropertisSourceWithConfigurationProperties dataSourcePropertisSourceWithConfigurationProperties;

        @Bean(value = "mydataSource")
        public DataSource initDataSource() {
                Properties properties = new Properties();
                properties.setProperty("url","jdbc:mysql://127.0.0.1:3306/test?useSSL=false");
                properties.setProperty("driver","com.mysql.jdbc.Driver");
                properties.setProperty("username","root");
                properties.setProperty("password","root");

                try {
                        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
                        return dataSource;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null ;
        }

        @Bean(value = "mydataSourceWithEnviroment")
        public DataSource initDataSourceFromEnviroment() {

                Properties properties = new Properties();
                properties.setProperty("driver",environment.getProperty("database.driver"));
                properties.setProperty("url",environment.getProperty("database.url"));
                properties.setProperty("username",environment.getProperty("database.username"));
                properties.setProperty("password",environment.getProperty("database.password"));

                try {
                        BasicDataSource dataSource1 = BasicDataSourceFactory.createDataSource(properties);
                        return dataSource1;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null ;

        }

        @Bean(value = "mydataSourceWithPropertySource")
        public DataSource initDataSourceWithPropertySource() {
                Properties properties = new Properties();
                properties.setProperty("driver",datasourceProperties.getDriver());
                properties.setProperty("url",datasourceProperties.getUrl());
                properties.setProperty("username",datasourceProperties.getUsername());
                properties.setProperty("password",datasourceProperties.getPassword());

                try {
                        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
                        return dataSource;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null ;
        }

        @Bean(value = "mydataSourceWithPropertySourceAndConfigurationProperties")

        public DataSource initDataSourceWithPropertySourceAndConfigurationProperties() {
                Properties properties = new Properties();
                properties.setProperty("driver",dataSourcePropertisSourceWithConfigurationProperties.getDriver());
                properties.setProperty("url",dataSourcePropertisSourceWithConfigurationProperties.getUrl());
                properties.setProperty("username",dataSourcePropertisSourceWithConfigurationProperties.getUsername());
                properties.setProperty("password",dataSourcePropertisSourceWithConfigurationProperties.getPassword());

                try {
                        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
                        return dataSource;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null ;
        }

}
