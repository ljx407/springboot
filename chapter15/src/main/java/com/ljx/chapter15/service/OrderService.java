package com.ljx.chapter15.service;

import com.ljx.chapter15.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(Long id);

    Order insertOrder(Order order);

    Integer batchInsertOrder(List<Order> orderList);

    void deleteAll();

    Integer findCount() ;
}
