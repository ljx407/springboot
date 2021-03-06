package com.ljx.chapter3_1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDaoWithPropertySource extends AbstractUserDao {

    @Autowired
    @Qualifier("mydataSourceWithPropertySource")
    private DataSource dataSource;

    @Override
    DataSource getDataSource() {
        return this.dataSource;
    }
}
