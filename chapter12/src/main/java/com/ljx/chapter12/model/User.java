package com.ljx.chapter12.model;

import com.ljx.chapter12.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("User")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    private Long id ;
    private String userName ;
    private String memo;
    private SexEnum sex;
    private String pass;
    private String available;
}
