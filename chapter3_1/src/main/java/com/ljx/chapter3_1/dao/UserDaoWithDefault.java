package com.ljx.chapter3_1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDaoWithDefault extends AbstractUserDao{

    @Autowired
    @Qualifier("mydataSource")
    private DataSource dataSource;

    @Override
    DataSource getDataSource() {
        return this.dataSource;
    }
}
