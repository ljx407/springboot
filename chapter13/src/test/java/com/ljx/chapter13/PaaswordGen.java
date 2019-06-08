package com.ljx.chapter13;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@RunWith(JUnit4.class)
public class PaaswordGen {

    @Test
    public void test(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        log.info(bCryptPasswordEncoder.encode("123"));
    }


}
