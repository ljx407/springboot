package com.ljx.chapter10springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id ;
    private String userName ;
    private String sex ;
    private String memo;
}
