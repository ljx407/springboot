package com.ljx.chapter9.model;

import com.ljx.chapter9.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private Long id ;
    private String userName ;
    private SexEnum sex ;
    private String memo ;
}
