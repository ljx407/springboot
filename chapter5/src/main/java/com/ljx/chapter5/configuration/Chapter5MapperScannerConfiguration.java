package com.ljx.chapter5.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@MapperScan("com.ljx.chapter5.mapper")
@Import(DatabaseConfiguration.class)
public class Chapter5MapperScannerConfiguration {


}
