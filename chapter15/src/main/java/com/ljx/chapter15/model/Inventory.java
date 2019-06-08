package com.ljx.chapter15.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("Inventory")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Inventory {
    private Long id ;
    private Long productId ;
    private Long stock ;
    private Integer version;
}
