package com.ljx.chapter17client.controller;

import com.google.common.collect.ImmutableMap;
import com.ljx.chapter17client.model.User;
import com.ljx.chapter17client.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate ;

    @GetMapping("/")
    public User getUser() {
        return userService.getUserById(1L);
    }

    @PostMapping("/")
    public Map<String,Object> addUser() {
        User peggy = User.builder()
                .userName("peggy")
                .level("2")
                .note("hello feign")
                .build();
        return userService.addUser(peggy);
    }

    @PostMapping("/{username}")
    public Map<String,Object> getProduct(@PathVariable("username") String username, @RequestHeader("userId") Long userId) {
        return userService.updateUser(username,userId);
    }

    @GetMapping("/ribbon")
    public User getProductByRibbon() {
        String url = "http://USER/user/1";
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    @GetMapping("/timeout")
    @HystrixCommand(
            fallbackMethod = "hystrixError",
            commandProperties = {
                  @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
            })
    public Map<String,Object> timeout() {
        User user = userService.timeout();
        return ImmutableMap.of("code", "20000", "message", String.format("username : %s", user.getUserName()));
    }

    public Map<String,Object> hystrixError() {
        return ImmutableMap.of("code","40004","message","hystrix");
    }


}
