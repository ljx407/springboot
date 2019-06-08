package com.ljx.chapter15.service.impl;

import com.ljx.chapter15.dto.OrderDto;
import com.ljx.chapter15.model.Inventory;
import com.ljx.chapter15.model.Order;
import com.ljx.chapter15.model.Product;
import com.ljx.chapter15.service.InventoryService;
import com.ljx.chapter15.service.OrderService;
import com.ljx.chapter15.service.ProductService;
import com.ljx.chapter15.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OrderService orderService ;

    @Autowired
    private InventoryService inventoryService ;

    @Autowired
    private ConversionService conversionService ;

    @Autowired
    private ProductService productService ;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public synchronized void purchase(OrderDto orderDto) {

        if(!hasEnoughStock(orderDto.getProductId(),orderDto.getQuantity())) {
            log.info("{} hasnot enough stock", orderDto.getProductId());
            return ;
        }
        inventoryService.decreaseInventory(orderDto.getProductId(),orderDto.getQuantity());
        orderService.insertOrder(orderDtoConvertToOrder(orderDto));
        log.info("purchase done");

    }

    @Override
    @Transactional
    public void purchaseWithNoLock(OrderDto orderDto) {

        if(!hasEnoughStock(orderDto.getProductId(),orderDto.getQuantity())) {
            log.info("{} hasnot enough stock", orderDto.getProductId());
            return ;
        }
        inventoryService.decreaseInventory(orderDto.getProductId(),orderDto.getQuantity());
        orderService.insertOrder(orderDtoConvertToOrder(orderDto));
        log.info("purchase done");

    }

    @Override
    @Transactional
    public void purchaseWithDBLock(OrderDto orderDto) {

        if(!hasEnoughStock(orderDto.getProductId(),orderDto.getQuantity())) {
            log.info("{} hasnot enough stock", orderDto.getProductId());
            return ;
        }
        inventoryService.decreaseInventory(orderDto.getProductId(),orderDto.getQuantity());
        orderService.insertOrder(orderDtoConvertToOrder(orderDto));
        log.info("purchase done");

    }

    @Override
    @Transactional
    public void purchaseWithVersionLock(OrderDto orderDto) {

        for(int i =0 ;i< 3; i++) {
            log.info("######## handleCount : {}" , i);
            Inventory inventoryStock = inventoryService.findStock(orderDto.getProductId());
            log.info("thread:{},productId:{},version:{},handleCount:{}",Thread.currentThread().getName(),orderDto.getProductId(),inventoryStock.getVersion(),i);
            if (inventoryStock.getStock() < orderDto.getQuantity()) {
                log.info("productId : {} ,has not enough stock",orderDto.getProductId());
                return;
            }
            int result = inventoryService.decreaseInventoryWithVersion(orderDto.getProductId(), orderDto.getQuantity(), inventoryStock.getVersion());
            if (result == 1) {
                orderService.insertOrder(orderDtoConvertToOrder(orderDto));
                log.info("purchase done");
                return;
            }
        }

    }

    @Override
    public void purchaseWithRedisLock(OrderDto orderDto) {
        String stockKey = orderDto.getProductId()+"_stock";
        String orderValue = orderDto.getUserId() +"-"+orderDto.getProductId()+"-"+orderDto.getQuantity();
        Object productIdStock = redisTemplate.opsForValue().get(stockKey);
        Long stock = conversionService.convert(productIdStock, Long.class);
        Long purchaseQuantity = conversionService.convert(orderDto.getQuantity(), Long.class);
        if(stock >= purchaseQuantity) {
            redisTemplate.opsForValue().decrement(stockKey);
            redisTemplate.opsForList().leftPush("order_list",orderValue);
        }

    }

    @Override
    public void restore(Long productId) {
        orderService.deleteAll();
        inventoryService.restoreInventory(productId);
        redisTemplate.opsForValue().set("1_stock","100");
        redisTemplate.delete("order_list");
    }

    @Override
    public Map<String, Object> getResult(Long userId, Long productId) {
        Inventory stock = inventoryService.findStock(productId);
        Integer count = orderService.findCount();
        Map<String, Object> result = new HashMap<>();
        result.put("stock",stock.getStock());
        result.put("version",stock.getVersion());
        result.put("count",count);
        return result;
    }

    private Order orderDtoConvertToOrder(OrderDto orderDto) {

        Product product = productService.findProductById(orderDto.getProductId());
        BigDecimal quantity = conversionService.convert(orderDto.getQuantity(), BigDecimal.class);

        return Order.builder()
                .productId(orderDto.getProductId())
                .quantity(orderDto.getQuantity())
                .amount(product.getPrice().multiply(quantity))
                .userId(orderDto.getUserId())
                .build();
    }


    private boolean hasEnoughStock(Long productId,Integer quantity) {
        Inventory stock = inventoryService.findStockWithLock(productId);
        Long currentStock = Optional.ofNullable(stock).map(item -> item.getStock()).orElse(-1L);
        Long decreaseQuantity = conversionService.convert(quantity, Long.class);
        return currentStock.compareTo(decreaseQuantity) >= 0;

    }
}
