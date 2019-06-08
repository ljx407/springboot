package com.ljx.chapter15.dao;

import com.ljx.chapter15.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDao {

    List<Order> findAll();

    Order findById(Long id);

    void insertOrder(Order order);

    Integer batchInsertOrder(@Param("orderList") List<Order> orderList);

    void deleteAll();

    Integer findCount();

}
