package com.ljx.chapter15.schedules;

import com.ljx.chapter15.model.Order;
import com.ljx.chapter15.model.Product;
import com.ljx.chapter15.service.InventoryService;
import com.ljx.chapter15.service.OrderService;
import com.ljx.chapter15.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AsyncOrderHandler {

    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired
    private OrderService orderService ;

    @Autowired
    private ConversionService conversionService ;

    @Autowired
    private ProductService productService ;

    @Autowired
    private InventoryService inventoryService ;

    @Scheduled(fixedDelay = 2000)
    public void asyncOrderHandler() {
        log.info("AsyncOrderHandler handle begin ..... , thread name : {} ", Thread.currentThread().getName());
        List<String> orderStrLists = new ArrayList<>();
        while (orderStrLists.size() <= 100) {
            String orderStr = (String)redisTemplate.opsForList().rightPop("order_list");
            if(orderStr == null) {
                break;
            }
            if(!validStr(orderStr)) {
                continue;
            }
            orderStrLists.add(orderStr);
        }


        final Map<Long, Product> idAssnProductMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(orderStrLists)) {
            List<Long> productIds = orderStrLists.stream().map(item -> conversionService.convert(item.split("-")[1],Long.class)).collect(Collectors.toList());
            List<Product> productListByIds = productService.findProductListByIds(productIds);
            Map<Long, Product> temp = productListByIds.stream().collect(Collectors.toMap(item -> item.getId(), Function.identity()));
            idAssnProductMap.putAll(temp);

            Set<Long> modifyProductIds = temp.keySet();
            for (Long modifyProductId : modifyProductIds) {
                Object currentStock = redisTemplate.opsForValue().get(modifyProductId + "_stock");
                Integer curStock = conversionService.convert(currentStock, Integer.class);
                inventoryService.updateInventory(modifyProductId,curStock);
            }
        }

        if(!CollectionUtils.isEmpty(orderStrLists)) {
            List<Order> orderList = orderStrLists.stream().map(item -> convertToOrder(item,idAssnProductMap)).collect(Collectors.toList());
            orderService.batchInsertOrder(orderList);
        }

        if(redisTemplate.opsForList().size("order_list") <= 0) {
            log.info("orderStrLists is empty !");
            return ;
        }

    }

    @Scheduled(fixedRate = 2000)
    public void randomTestSchedule() {
        log.info("randomTestSchedule handle begin ..... , thread name : {} ", Thread.currentThread().getName());
    }

    private Order convertToOrder(String str,Map<Long,Product> idAssnProductMap) {
        String[] s = str.split("-");
        Assert.notNull(s,"入参错误");
        Assert.isTrue(s.length == 3,"入参错误");

        Integer quantity = conversionService.convert(s[2], Integer.class);
        Long productId = conversionService.convert(s[1], Long.class);
        BigDecimal price = Optional.ofNullable(idAssnProductMap.get(productId)).map(item -> item.getPrice()).orElse(BigDecimal.ZERO);
        BigDecimal amount = conversionService.convert(s[1],BigDecimal.class).multiply(price);
        return Order.builder()
                .userId(conversionService.convert(s[0],Long.class))
                .productId(productId)
                .quantity(quantity)
                .amount(amount)
                .build();

    }

    private boolean validStr(String str) {
        Pattern pattern = Pattern.compile("\\d\\-\\d\\-\\d");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
