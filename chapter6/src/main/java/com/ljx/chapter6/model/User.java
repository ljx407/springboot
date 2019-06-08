package com.ljx.chapter6.model;

import com.ljx.chapter6.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Alias("user")
public class User {

    private Long id ;
    private String userName;
    private String note ;
    private SexEnum sex ;
}
