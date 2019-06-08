package com.ljx.chapter15.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Alias("Product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private Long id ;
    private String productName ;
    private BigDecimal price ;
}
