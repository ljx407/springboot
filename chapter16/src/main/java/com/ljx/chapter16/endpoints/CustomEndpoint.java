package com.ljx.chapter16.endpoints;

import com.google.common.collect.ImmutableMap;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(
        id = "ljxendpoint",
        enableByDefault = false
)
public class CustomEndpoint {

    @ReadOperation
    public Map<String,Object> test() {
        return ImmutableMap.of("code","123","message","hello jasonliu");
    }
}
