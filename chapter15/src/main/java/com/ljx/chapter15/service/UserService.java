package com.ljx.chapter15.service;

import com.ljx.chapter15.dto.OrderDto;

import java.util.Map;

public interface UserService {

    void purchase(OrderDto orderDto);

    void purchaseWithNoLock(OrderDto orderDto);

    void purchaseWithDBLock(OrderDto orderDto);

    void purchaseWithVersionLock(OrderDto orderDto);

    void purchaseWithRedisLock(OrderDto orderDto);

    void restore(Long productId);

    Map<String, Object> getResult(Long userId, Long productId);
}
