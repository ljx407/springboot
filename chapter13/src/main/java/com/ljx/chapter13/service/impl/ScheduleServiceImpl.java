package com.ljx.chapter13.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Slf4j
@Service
public class ScheduleServiceImpl {

    @Scheduled(fixedRate = 1000)
    @Async
    public void job1() {
        log.info("job1 excute ! currentThread : {} , date:{}",Thread.currentThread().getName(), ZonedDateTime.now());
    }

    @Scheduled(cron = "0/3 * * ? * *")
    @Async
    public void job2() {
        log.info("job2 excute ! currentThread : {} , date:{}",Thread.currentThread().getName(), ZonedDateTime.now());

    }
}
