package com.ljx.chapter12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Slf4j
@RunWith(JUnit4.class)
public class PaasWordEncode {

    @Test
    public void getPaas() {
        // 9ffffbb4d128821dd3c86e9eeec62415bce791721a0b25880e3110c076030c7766216ffeb846701e
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("chapter12");
        log.info(passwordEncoder.encode("123"));
    }
}
