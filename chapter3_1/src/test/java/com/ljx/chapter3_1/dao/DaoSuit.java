package com.ljx.chapter3_1.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserDaoWithDefaultTest.class,
        UserDaoWithEnviromentTest.class,
        UserDaoWithPropertySourceTest.class,
        UserDaoWithPropertySourceAndConfigurationPropertiesTest.class,
        TestConfigDaoTest.class,
        TestDaoTest.class
})
public class DaoSuit {
}
