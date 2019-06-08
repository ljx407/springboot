package com.ljx.chapter15.model;


import com.ljx.chapter15.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private Long id ;
    private String userName ;
    private String note ;
    private SexEnum sex ;
}
