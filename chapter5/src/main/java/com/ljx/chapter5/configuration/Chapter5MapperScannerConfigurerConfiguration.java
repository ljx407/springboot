package com.ljx.chapter5.configuration;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfiguration.class)
public class Chapter5MapperScannerConfigurerConfiguration {

//    @Bean
//    public DataSource initDataSource() {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/chapter5?useSSL=false");
//        hikariConfig.setUsername("root");
//        hikariConfig.setPassword("root");
//        return new HikariDataSource(hikariConfig);
//
//    }
//
//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory initSqlSessionFactoryBean() {
////        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
////        Environment environment = new Environment("dev", new JdbcTransactionFactory(),initDataSource());
////        configuration.setEnvironment(environment);
////        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
////        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(configuration);
////        return sessionFactory;
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(initDataSource());
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.ljx.chapter5.model");
//        sqlSessionFactoryBean.setMapperLocations(new Resource[]{new ClassPathResource("mapper/BookMapper.xml")});
//        sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new BookTypeTypeHandler()});
//        try {
//            return sqlSessionFactoryBean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null ;
//    }

    @Bean
    public MapperScannerConfigurer initMapperSannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.ljx.chapter5.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
