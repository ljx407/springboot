package com.ljx.chapter17user.controller;

import com.google.common.collect.Maps;
import com.ljx.chapter17user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public User getUserByid(@PathVariable("id") Long id) {
        return User.builder()
                .id(id)
                .level("1")
                .note("hello")
                .userName("jasonliu")
                .build();
    }

    @PostMapping("/")
    public Map<String,Object> addUser(@RequestBody User user) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("code","20000");
        result.put("message",String.format("%s 插入成功！", user.getUserName()));
        return result;
    }

    @PostMapping("/{username}")
    public Map<String,Object> updateUser(@PathVariable("username") String username, @RequestHeader("userId") Long userId) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("code","20000");
        result.put("message",String.format("%s 修改成功！ id: %s", username, userId));
        return result;
    }

    @GetMapping("/timeout")
    public User getTimeout() {
        log.info("usercontroller getTimeout begin ....");
        int sleepTime = RandomUtils.nextInt(3000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("usercontroller getTimeout end ....");

        return User.builder()
                .id(1L)
                .level("1")
                .note("hello")
                .userName("jasonliu")
                .build();
    }
}
