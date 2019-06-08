package com.ljx.chapter10springmvc.configuration;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataSourceConfiguration.class)
@PropertySource("classpath:mybatis.properties")
@MapperScan("com.ljx.chapter10springmvc.dao")
public class MyBatisConfiguration {

    @Value("${mybatis.mapper.scan.package}")
    private String mapperScanPackage;

    @Value("${mybatis.alias.package}")
    private String aliasPackage;

    @Value("${mybatis.typehandler.package}")
    private String typeHandlerPackage;

    @Value("${mybatis.mappers}")
    private Resource[] resources;


//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }


    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean initSqlSessionFactoryBean(DataSource dataSource) {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setTypeAliasesPackage(aliasPackage);
//        sqlSessionFactoryBean.setTypeHandlersPackage(typeHandlerPackage);
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

//    @Bean
//    public MapperScannerConfigurer initMapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("com.ljx.chapter10springmvc.dao");
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setProcessPropertyPlaceHolders(true);
//        return mapperScannerConfigurer;
//    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }


}
