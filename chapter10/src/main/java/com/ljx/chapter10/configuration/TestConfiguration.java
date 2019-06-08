package com.ljx.chapter10.configuration;

import com.ljx.chapter10.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class TestConfiguration {

    public HelloController initHelloController() {
        return new HelloController();
    }
}
