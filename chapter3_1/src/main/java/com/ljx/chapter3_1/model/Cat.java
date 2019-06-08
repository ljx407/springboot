package com.ljx.chapter3_1.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Primary
public class Cat implements Animal {

    @Override
    public void use() {
        log.info("捉老鼠。。。。");
    }
}
