package com.ljx.chapter5.unit;

import com.ljx.chapter5.configuration.Chapter5MapperScannerConfiguration;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataSourceShowTest.class,
        Chapter5ConfigurationTest.class,
        Chapter5MapperScannerConfigurerConfigurationTest.class,
        Chapter5MapperScannerConfiguration.class
})
public class UnitSuit {
}
