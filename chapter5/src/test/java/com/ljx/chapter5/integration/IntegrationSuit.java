package com.ljx.chapter5.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserServiceTest.class,
        JdbcTemplateUserServiceTest.class,
        EmployeeServiceTest.class,
        BookServiceTest.class
})
public class IntegrationSuit {
}
