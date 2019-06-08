package com.ljx.chapter5;

import com.ljx.chapter5.integration.IntegrationSuit;
import com.ljx.chapter5.unit.UnitSuit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IntegrationSuit.class,
        UnitSuit.class
})
public class SuitTest {
}
