package com.ljx.chapter10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljx.chapter10.enums.SexEnum;
import com.ljx.chapter10.model.User;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper ;


    @Test
    @SneakyThrows
    public void testFindAll() {
        mockMvc.perform(get("/user/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("jasonliu")));
    }

    @Test
    @SneakyThrows
    public void testFindByUserId() {
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("jasonliu")));
    }

    @Test
    @SneakyThrows
    @Transactional
    @Rollback
    public void testInserUser() {
        User peggy = User.builder().sex(SexEnum.FEMAIL).memo("world").userName("peggy").build();

        mockMvc.perform(
                post("/user/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(peggy))
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("peggy")));
    }

    @Test
    @SneakyThrows
    @Transactional
    @Rollback
    public void testUpdateUser() {
        User test = User.builder().id(999L).sex(SexEnum.FEMAIL).memo("test99").userName("test99").build();

        mockMvc.perform(
                put("/user/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(test))
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test99")));
    }

    @Test
    @SneakyThrows
    @Transactional
    @Rollback
    public void testDeleteUserById() {

        mockMvc.perform(
                delete("/user/999")
        )
                .andExpect(status().isOk());
    }
}
