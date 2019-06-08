package com.ljx.chapter15.controller;

import com.ljx.chapter15.dto.OrderDto;
import com.ljx.chapter15.dto.ResultDto;
import com.ljx.chapter15.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    private AtomicInteger threadCount = new AtomicInteger(1);


    @Autowired
    private UserService userService ;

    @GetMapping("/order")
    public String toPurchase() {
        return "purchase";
    }

    @PostMapping("/orderLock")
    @ResponseBody
    public ResultDto purchase(@RequestBody OrderDto orderDto) {

        Thread.currentThread().setName("thread-" + threadCount.getAndIncrement());
        log.info("current object: {}, thread-name : {}",this, Thread.currentThread().getName());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        userService.purchase(orderDto);
        log.info("purchase controller done");

        stopWatch.stop();
        log.info("purchase cost : {}",stopWatch.getTotalTimeMillis());
        return ResultDto.builder()
                .code("200")
                .message("success")
                .build();
    }

    @PostMapping("/orderNoLock")
    @ResponseBody
    public ResultDto purchaseNoLock(@RequestBody OrderDto orderDto) {

        Thread.currentThread().setName("thread-" + threadCount.getAndIncrement());
        log.info("current object: {}, thread-name : {}",this, Thread.currentThread().getName());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        userService.purchaseWithNoLock(orderDto);
        log.info("purchaseNoLock controller done");

        stopWatch.stop();
        log.info("purchaseNoLock cost : {}",stopWatch.getTotalTimeMillis());
        return ResultDto.builder()
                .code("200")
                .message("success")
                .build();
    }

    @PostMapping("/orderDBLock")
    @ResponseBody
    public ResultDto purchaseWithDBLock(@RequestBody OrderDto orderDto) {

        Thread.currentThread().setName("thread-" + threadCount.getAndIncrement());
        log.info("current object: {}, thread-name : {}",this, Thread.currentThread().getName());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        userService.purchaseWithDBLock(orderDto);
        log.info("orderDBLock controller done");

        stopWatch.stop();
        log.info("orderDBLock cost : {}",stopWatch.getTotalTimeMillis());
        return ResultDto.builder()
                .code("200")
                .message("success")
                .build();
    }

    @PostMapping("/orderVersionLock")
    @ResponseBody
    public ResultDto purchaseWithVersionLock(@RequestBody OrderDto orderDto) {

        Thread.currentThread().setName("thread-" + threadCount.getAndIncrement());
        log.info("current object: {}, thread-name : {}",this, Thread.currentThread().getName());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        userService.purchaseWithVersionLock(orderDto);
        log.info("purchaseWithVersionLock controller done");

        stopWatch.stop();
        log.info("purchaseWithVersionLock cost : {}",stopWatch.getTotalTimeMillis());
        return ResultDto.builder()
                .code("200")
                .message("success")
                .build();
    }

    @PostMapping("/orderRedisLock")
    @ResponseBody
    public ResultDto purchaseWithRedisLock(@RequestBody OrderDto orderDto) {

        Thread.currentThread().setName("thread-" + threadCount.getAndIncrement());
        log.info("current object: {}, thread-name : {}",this, Thread.currentThread().getName());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        userService.purchaseWithRedisLock(orderDto);
        log.info("purchaseWithVersionLock controller done");

        stopWatch.stop();
        log.info("purchaseWithVersionLock cost : {}",stopWatch.getTotalTimeMillis());
        return ResultDto.builder()
                .code("200")
                .message("success")
                .build();
    }

    @PostMapping("/order/{productId}")
    @ResponseBody
    public ResultDto restore(@PathVariable("productId") Long productId) {
        userService.restore(productId);
        return ResultDto.builder()
                .code("200")
                .message("success")
                .build();
    }

    @GetMapping("/{userId}/{productId}")
    @ResponseBody
    public ResultDto queryResult(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        Map<String, Object> result = userService.getResult(userId, productId);

        return ResultDto.builder()
                .code("200")
                .message("success")
                .data(result)
                .build();
    }

}
