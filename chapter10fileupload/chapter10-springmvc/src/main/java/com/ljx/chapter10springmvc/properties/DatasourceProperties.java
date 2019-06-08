package com.ljx.chapter10springmvc.properties;


import org.springframework.beans.factory.annotation.Value;

public class DatasourceProperties {
    @Value("${datasource.url}")
    private String url ;
    @Value("${datasource.username}")
    private String username ;
    @Value("${datasource.password}")
    private String password ;
}
