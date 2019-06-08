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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private ObjectMapper objectMapper ;

    @Autowired
    private MockMvc mockMvc ;

    @Test
    @SneakyThrows
    public void testGetParamWithoutAnnotation() {
        mockMvc.perform(
                get("/hello/paramWithoutAnnotation")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "jasonliu")
                        .param("age", "18")
                        .param("memo", "hello")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name",is("jasonliu")))
                .andExpect(jsonPath("$.age",is(18)))
                .andExpect(jsonPath("$.memo",is("hello")));
    }

    @Test
    @SneakyThrows
    public void testGetParamWithoutAnnotation1() {
        mockMvc.perform(
                get("/hello/paramWithoutAnnotation")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "jaonsliu")
                        .param("age", "18")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name",is("jaonsliu")))
                .andExpect(jsonPath("$.age",is(18)))
                .andExpect(jsonPath("$.memo", nullValue()));
    }

    @Test
    @SneakyThrows
    public void testGetParamWithAnnotation() {
        mockMvc.perform(
                get("/hello/paramWithAnnotation")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "jaonsliu")
                        .param("age", "18")
                        .param("memo", "hello")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResolvedException()
                .getMessage()
                .contentEquals("Required String parameter 'user_name' is not present");
//                .andExpect(content().string("Required String parameter 'user_name' is not present"));
//                .andExpect(jsonPath("$.message", is("Required String parameter 'user_name' is not present")));
//                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
//                .andExpect(jsonPath("$.status", is(400)))
//                .andExpect(jsonPath("$.errors").isArray())
//                .andExpect(jsonPath("$.errors", hasSize(1)))
//                .andExpect(jsonPath("$.errors", hasItem("Required String parameter 'user_name' is not present")));

    }

    @Test
    @SneakyThrows
    public void testGetParamWithAnnotationrequired() {
        mockMvc.perform(
                get("/hello/paramWithAnnotationrequired")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("user_name", "jasonliu")
                        .param("user_age", "18")
                        .param("user_memo", "hello")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("jasonliu")))
                .andExpect(jsonPath("$.age",is(18)))
                .andExpect(jsonPath("$.memo",is("hello")));

    }

    @Test
    @SneakyThrows
    public void testGetParamWithArray() {
        mockMvc.perform(
                get("/hello/paramWithArray")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "jasonliu,peggy")
                        .param("age", "18")
                        .param("memo", "hello")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", hasSize(2)))
                .andExpect(jsonPath("$.name[0]",is("jasonliu")))
                .andExpect(jsonPath("$.name[1]",is("peggy")))
                .andExpect(jsonPath("$.age",hasSize(1)))
                .andExpect(jsonPath("$.age[0]",is(18)))
                .andExpect(jsonPath("$.memo",hasSize(1)))
                .andExpect(jsonPath("$.memo[0]",is("hello")));

    }

    @Test
    @SneakyThrows
    public void testGetParamWithJason() {
        User jasonliu = User.builder().id(1L).userName("jasonliu").memo("hello").sex(SexEnum.MAIL).build();

        mockMvc.perform(
                get("/hello/paramWithJason")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(jasonliu))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.userName",is("jasonliu")))
                .andExpect(jsonPath("$.sex",is("MAIL")))
                .andExpect(jsonPath("$.memo",is("hello")));

    }

    @Test
    @SneakyThrows
    public void testGetParamWithJason1() {
        Map<String, Object> param = new HashMap<>();
        param.put("id","2");
        param.put("userName","peggy");
        param.put("sex","2");
        param.put("memo","helloworld");
        mockMvc.perform(
                get("/hello/paramWithJason")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(param))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(2)))
                .andExpect(jsonPath("$.userName",is("peggy")))
                .andExpect(jsonPath("$.sex",is("2")))
                .andExpect(jsonPath("$.memo",is("helloworld")));

    }

    @Test
    @SneakyThrows
    public void testGetParamWithJason2() {
        mockMvc.perform(
                get("/hello/paramWithForm")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id","2")
                        .param("userName","peggy")
                        .param("sex","2")
                        .param("memo","helloworld")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(2)))
                .andExpect(jsonPath("$.userName",is("peggy")))
                .andExpect(jsonPath("$.sex",is("FEMAIL")))
                .andExpect(jsonPath("$.memo",is("helloworld")));

    }

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
//        String female = objectMapper.writeValueAsString(SexEnum.FEMAIL);
//        System.out.println(female);
//
//        SexEnum deFemail = objectMapper.readValue("\"FEMAIL\"", SexEnum.class);
//        System.out.println(deFemail);

        String mail = objectMapper.writeValueAsString(SexEnum.MAIL);
        System.out.println(mail);

        SexEnum deMailCorrect = objectMapper.readValue("\"1\"", SexEnum.class);
        System.out.println(deMailCorrect);

        SexEnum deMailCorrect1 = objectMapper.readValue("\"11\"", SexEnum.class);
        System.out.println(deMailCorrect1);

        SexEnum deMailError = objectMapper.readValue("\"MAIL\"", SexEnum.class);
        System.out.println(deMailError);

    }

    @Test
    @SneakyThrows
    public void testGetPathVariable() {

        mockMvc.perform(
                get("/hello/jasonliu")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("jasonliu")))
                .andExpect(jsonPath("$.age",nullValue()))
                .andExpect(jsonPath("$.memo",nullValue()));

    }

    @Test
    @SneakyThrows
    public void testGetParamWithFormat() {

        mockMvc.perform(
                get("/hello/paramWithFormat")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("date", "2019-01-01 12:12:21")
                        .param("money", "12,333,333.44")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is("2019-04-29 12:17:59")))
                .andExpect(jsonPath("$.money", is("12,333,333.44")));

    }


}
