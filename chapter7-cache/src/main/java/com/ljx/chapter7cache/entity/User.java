package com.ljx.chapter7cache.entity;

import com.ljx.chapter7cache.enums.SexEnum;
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
    private String name ;
    private String memo ;
    private SexEnum sex ;
}
