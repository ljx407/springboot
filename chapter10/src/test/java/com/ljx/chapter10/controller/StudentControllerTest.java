package com.ljx.chapter10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private ObjectMapper objectMapper ;

    @Autowired
    private MockMvc mockMvc ;

    @Test
    @SneakyThrows
    public void testGetParamWithoutAnnotation() {
        mockMvc.perform(
                get("/stu/paramWithAnnotation")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1")
                        .param("userName", "jasonliu")
                        .param("password", "123")
                        .param("age", "18")
                        .param("ageDisplay", "2")
                        .param("sex", "1")
                        .param("money", "20")
                        .param("email", "ljx4@")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void testGetParamWithoutAnnotationError() {
        mockMvc.perform(
                get("/stu/paramWithAnnotation")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1")
                        .param("userName", "jasonliu")
                        .param("password", "1234")
                        .param("age", "18")
                        .param("ageDisplay", "2")
                        .param("sex", "1")
                        .param("money", "20")
                        .param("email", "ljx4@")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("密码不匹配");
    }




}
