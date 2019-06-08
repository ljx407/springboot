package com.ljx.chapter17client;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class Chapter17ClientApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(RandomUtils.nextInt(3000));
    }

}
