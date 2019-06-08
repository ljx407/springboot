package com.ljx.chapter3_1.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:jdbc.properties")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DatasourceProperties {
    @Value("${database.driver}")
    private String driver ;
    @Value("${database.url}")
    private String url ;
    @Value("${database.username}")
    private String username ;
    @Value("${database.password}")
    private String password ;

}
