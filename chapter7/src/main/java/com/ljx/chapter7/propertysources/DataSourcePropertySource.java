package com.ljx.chapter7.propertysources;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:datasource.properties")
@ConfigurationProperties(prefix = "datasource")
@Data
public class DataSourcePropertySource {

    private String url ;
    private String username ;
    private String password ;
}
