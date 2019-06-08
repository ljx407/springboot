package com.ljx.chapter17user.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User implements Serializable {

    private Long id ;
    private String userName;
    private String note ;
    private String level;
}
