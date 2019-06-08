package com.ljx.chapter5.model;

import com.ljx.chapter5.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id ;
    private String userName;
    private String note;
    private SexEnum sex ;
}
