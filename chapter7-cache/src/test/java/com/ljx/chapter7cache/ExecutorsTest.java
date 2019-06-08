package com.ljx.chapter7cache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.*;

@Slf4j
@RunWith(JUnit4.class)
public class ExecutorsTest {

    @Test
    public void test() {
        Runnable r = () -> log.info("#####running");
        Callable<String> c = ()-> {log.info("#####Callable");return "hello";};
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(r);
        Future<String> submit = executorService.submit(c);
        try {
            String s = submit.get(2, TimeUnit.SECONDS);
            log.info("callable result:{}",s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();

    }


    @Test
    public void test1() {

    }
}
