package com.ljx.chapter11.controller;

import com.ljx.chapter11.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String index() {
        return "hello jasonliu";
    }

    @GetMapping("/ex")
    public String testEx() {
        throw new RuntimeException("error");
//        return "hello jasonliu";
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        User user = restTemplate.getForObject("http://127.0.0.1:8080/user/{id}", User.class, id);
        return user;
    }

    @GetMapping("/user2/{id}")
    public ResponseEntity<User> getUser2(@PathVariable("id") Long id) {
        ResponseEntity<User> forEntity = restTemplate.getForEntity("http://127.0.0.1:8080/user/{id}", User.class, id);
        HttpStatus statusCode = forEntity.getStatusCode();
        log.info("####statusCode : " + statusCode.name());
        return forEntity;
    }

    @GetMapping("/user3/")
    public User getUser3() {
        User user = User.builder().userName("mumu").memo("hello").build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);

        User userResult = restTemplate.postForObject("http://127.0.0.1:8080/user/", httpEntity, User.class);
        return userResult;
    }
}
