package com.ljx.chapter11.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.ljx.chapter11.mappers")
@PropertySource("classpath:datasource.properties")
public class DataSourceConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setPoolName("chapter11");
//        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/chapter11");
//        hikariConfig.setPassword("root");
//        hikariConfig.setUsername("root");
//        hikariConfig.setConnectionTestQuery("select 1");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setPoolName("chapter11");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/chapter11");
        hikariConfig.setPassword("root");
        hikariConfig.setUsername("root");
        hikariConfig.setConnectionTestQuery("select 1");

//        hikariConfig.setMaximumPoolSize(10);
//        hikariConfig.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
//        hikariConfig.addDataSourceProperty("url", "jdbc:mysql://localhost:3306/chapter11");
//        hikariConfig.addDataSourceProperty("user", "root");
//        hikariConfig.addDataSourceProperty("password", "root");

//        hikariConfig.setMaximumPoolSize(10);
//        hikariConfig.setDataSourceClassName(environment.getProperty("datasource.driver"));
//        hikariConfig.addDataSourceProperty("url", environment.getProperty("datasource.url"));
//        hikariConfig.addDataSourceProperty("user", environment.getProperty("datasource.username"));
//        hikariConfig.addDataSourceProperty("password", environment.getProperty("datasource.password"));

        // 这样设置导致执行失败，Cause: java.sql.SQLException: No database selected
//        hikariConfig.setMaximumPoolSize(10);
//        hikariConfig.setPoolName("chapter11");
//        hikariConfig.setDataSourceClassName(environment.getProperty("datasource.driver"));
//        hikariConfig.setJdbcUrl(environment.getProperty("datasource.url"));
//        hikariConfig.setUsername(environment.getProperty("datasource.username"));
//        hikariConfig.setPassword(environment.getProperty("datasource.password"));

//        MysqlDataSource mysqlDataSource = new MysqlDataSource();
//        mysqlDataSource.setURL(environment.getProperty("datasource.url"));
//        mysqlDataSource.setUser(environment.getProperty("datasource.username"));
//        mysqlDataSource.setPassword(environment.getProperty("datasource.password"));
//
//        hikariConfig.setPoolName("chapter11");
//        hikariConfig.setMaximumPoolSize(5);
//        hikariConfig.setMinimumIdle(2);
//        hikariConfig.setDataSource(mysqlDataSource);

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }

    @Bean
    public JdbcTemplate initJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    @Bean
    @SneakyThrows
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.alias.package"));
        sqlSessionFactoryBean.setTypeHandlersPackage(environment.getProperty("mybatis.typehandle.package"));

        // 设置查找器
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(environment.getProperty("mybatis.mapper.loacation"));

        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean;
    }
}
