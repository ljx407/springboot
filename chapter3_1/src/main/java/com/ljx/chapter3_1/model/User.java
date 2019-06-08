package com.ljx.chapter3_1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Component
public class User {
    @Value("1")
    private Long id ;
    @Value("Peggy")
    private String userName ;
    @Value("HelloWorld Peggy")
    private String note ;
}
