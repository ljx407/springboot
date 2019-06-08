package com.ljx.chapter7.model;

import com.ljx.chapter7.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    private Long id ;
    private String note ;
    private String username ;
    private SexEnum sex ;
}
