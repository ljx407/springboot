package com.ljx.chapter13.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloAysncServiceImpl {

    @Async
    public void testAysnc() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Current thread name : {}", Thread.currentThread().getName());
    }
}
