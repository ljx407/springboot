package com.ljx.chapter17client.service;

import com.ljx.chapter17client.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("user")
@Service
public interface UserService {

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") Long id);

    @PostMapping("/user/")
    Map<String,Object> addUser(@RequestBody User user);


    @PostMapping("/user/{username}")
    Map<String,Object> updateUser(@PathVariable("username") String username, @RequestHeader("userId") Long userId);

    @GetMapping("/user/timeout")
    User timeout();


}
