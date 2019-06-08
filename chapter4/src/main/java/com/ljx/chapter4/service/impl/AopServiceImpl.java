package com.ljx.chapter4.service.impl;

import com.ljx.chapter4.service.AopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AopServiceImpl implements AopService {
    @Override
    public void printGreeting(String username) {
        log.info("##### AopServiceImpl.printGreeting args:{}......", username);
    }
}
