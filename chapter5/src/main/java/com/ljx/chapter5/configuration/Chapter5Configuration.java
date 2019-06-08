package com.ljx.chapter5.configuration;

import com.ljx.chapter5.mapper.BookMapper;
import com.ljx.chapter5.mapper.BookMyBatisDaoForMapperFactoryBean;
import com.ljx.chapter5.service.BookService;
import com.ljx.chapter5.service.impl.BookServiceImplForBeanMapperFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(DatabaseConfiguration.class)
public class Chapter5Configuration {

//    @Bean
//    public DataSource initDataSource() {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/chapter5?useSSL=false");
//        hikariConfig.setUsername("root");
//        hikariConfig.setPassword("root");
//        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//        return dataSource ;
//    }
//
//
//    @Bean
//    public SqlSessionFactory initSqlSessionFactory()  {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean() ;
//        sqlSessionFactoryBean.setDataSource(initDataSource());
//        TypeHandler[] typeHandlers = new TypeHandler[]{new BookTypeTypeHandler()};
//        sqlSessionFactoryBean.setTypeHandlers(typeHandlers);
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.ljx.chapter5.model");
//        sqlSessionFactoryBean.setMapperLocations(new Resource[]{new ClassPathResource("mapper/BookMapper.xml")});
////        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
////        org.apache.ibatis.session.Configuration configuration =  new org.apache.ibatis.session.Configuration();
////        TransactionFactory transactionFactory = new JdbcTransactionFactory();
////        Environment environment = new Environment("develop",transactionFactory,initDataSource());
////        configuration.setEnvironment(environment);
////        configuration.getTypeHandlerRegistry().register(BookTypeTypeHandler.class);
////        return sqlSessionFactoryBuilder.build(configuration);
//        SqlSessionFactory object = null ;
//        try {
//            object = sqlSessionFactoryBean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return object;
//
//    }

    @Bean
    public MapperFactoryBean<BookMyBatisDaoForMapperFactoryBean> initMapperFactoryBean(@Autowired SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<BookMyBatisDaoForMapperFactoryBean> mapperFactoryBean = new MapperFactoryBean();
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        mapperFactoryBean.setMapperInterface(BookMyBatisDaoForMapperFactoryBean.class);
        return mapperFactoryBean;
    }

    @Bean("bookServiceForBeanMapperFactory")
    public BookService initBookService() {
        BookService bookService = new BookServiceImplForBeanMapperFactory();
        return bookService;
    }

    @Bean
    public MapperFactoryBean<BookMapper> initBookMyBatisDao(@Autowired SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<BookMapper> mapperFactoryBean = new MapperFactoryBean();
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        mapperFactoryBean.setMapperInterface(BookMapper.class);
        return mapperFactoryBean;
    }
}
