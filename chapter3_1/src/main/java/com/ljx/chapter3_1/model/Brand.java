package com.ljx.chapter3_1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Brand {
    private Long id ;
    private String brandName ;
    private String active ;
}
