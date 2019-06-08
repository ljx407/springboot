package com.ljx.chapter3_1.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseDataSource {
    String driver ;
    String url ;
    String username ;
    String password ;
}
