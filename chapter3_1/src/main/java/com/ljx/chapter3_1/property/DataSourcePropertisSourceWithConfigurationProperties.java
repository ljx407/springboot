package com.ljx.chapter3_1.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

//@Component
@PropertySource("classpath:jdbc.properties")
@ConfigurationProperties("database")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DataSourcePropertisSourceWithConfigurationProperties {

    private String driver ;
    private String url ;
    private String username ;
    private String password ;
}
