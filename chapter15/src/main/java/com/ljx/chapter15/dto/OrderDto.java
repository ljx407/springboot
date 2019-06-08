package com.ljx.chapter15.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDto {
    private Long productId;
    private Long userId;
    private Integer quantity;
}
