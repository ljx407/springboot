package com.ljx.chapter15.service.impl;

import com.ljx.chapter15.dao.OrderDao;
import com.ljx.chapter15.model.Order;
import com.ljx.chapter15.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    @Cacheable(cacheNames = "chapter15_order", key = "'order_' + #id")
    public Order findById(Long id) {
        log.info("findById invoke id : {}" , id);
        return orderDao.findById(id);
    }

    @Override
    @CachePut(cacheNames = "chapter15_order", key = "'order_' + #result.id")
    public Order insertOrder(Order order) {
        orderDao.insertOrder(order);
        return order;
    }

    @Override
    public Integer batchInsertOrder(List<Order> orderList) {
        if(CollectionUtils.isEmpty(orderList)) {
            return 0 ;
        }
        return orderDao.batchInsertOrder(orderList);
    }

    @Override
    public void deleteAll() {
        orderDao.deleteAll();
    }

    @Override
    public Integer findCount() {
        return orderDao.findCount();
    }
}
