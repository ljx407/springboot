package com.ljx.chapter10thymeleaf.model;

import com.ljx.chapter10thymeleaf.enums.SexEnum;
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
    private SexEnum sex ;
    private String memo ;

}
