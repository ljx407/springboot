package com.ljx.chapter3_1;

import com.ljx.chapter3_1.dao.DaoSuit;
import com.ljx.chapter3_1.unit.UnitSuit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UnitSuit.class,
        DaoSuit.class
})
public class TestSuit {
}
