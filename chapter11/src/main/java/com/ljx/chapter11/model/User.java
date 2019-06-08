package com.ljx.chapter11.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("User")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    private Long id ;
    private String userName;
    private String memo ;

}
