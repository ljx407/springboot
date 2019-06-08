package com.ljx.chapter6.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserMapperTest.class,
        RoleMapperTest.class,
        RoleBatchServiceTest.class,
        TransactionTest.class,
        PropagationServiceTest.class
})
public class UnitTest {
}
