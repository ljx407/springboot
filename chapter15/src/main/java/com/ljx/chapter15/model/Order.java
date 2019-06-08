package com.ljx.chapter15.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;

@Alias("Order")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order implements Serializable {
    private Long id ;
    private Long productId ;
    private Long userId ;
    private Integer quantity ;
    private BigDecimal amount;
}
