package com.ljx.chapter9react.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljx.chapter9react.entity.User;
import com.ljx.chapter9react.enums.SexEnum;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc ;

    @Autowired
    private ObjectMapper objectMapper ;


    @Test
    @SneakyThrows
    public void testFindAll() {
        mockMvc.perform(get("/users/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jasonliu")));

    }

    @Test
    @SneakyThrows
    public void testFindById() {
        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jasonliu")));

    }

    @Test
    @SneakyThrows
    public void testAddUser() {
        User peggy = User.builder().userName("peggy").sex(SexEnum.FEMAIL).memo("world").build();

        mockMvc.perform(
                post("/users/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(peggy)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("peggy")));

    }

    @Test
    @SneakyThrows
    public void testDeleteUser() {

        mockMvc.perform(
                delete("/users/4")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @SneakyThrows
    public void testUpdateUser() {
        User test = User.builder().id(999L).userName("test99").sex(SexEnum.FEMAIL).memo("test").build();
        mockMvc.perform(
                put("/users/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(test)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test99")));

    }
}
