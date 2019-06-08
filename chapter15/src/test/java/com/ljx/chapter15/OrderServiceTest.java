package com.ljx.chapter15;

import com.ljx.chapter15.model.Order;
import com.ljx.chapter15.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService ;

    @Test
    public void test() {
        for (int i = 0; i < 2; i++) {
            Order byId = orderService.findById(203L);
            log.info("order : {}" , byId.getId());
        }

    }

    @Test
    public void test10() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123"));
    }

    @Test
    public void testInsert() {
        Order order = Order.builder()
                .productId(1L)
                .userId(1L)
                .quantity(1)
                .amount(BigDecimal.ONE)
                .build();

        orderService.insertOrder(order);

    }
}
