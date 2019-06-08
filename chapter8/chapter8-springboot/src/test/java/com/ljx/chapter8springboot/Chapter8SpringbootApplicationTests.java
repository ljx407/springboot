package com.ljx.chapter8springboot;

import com.ljx.chapter8springboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter8SpringbootApplicationTests {

    @Autowired
    @Qualifier("primaryMongoTemplate")
    private MongoTemplate primaryMongoTemplate;

    @Autowired
    @Qualifier("secondaryMongoTemplate")
    private MongoTemplate secondMongoTemplate;

    @Autowired
    @Qualifier("thirdMongoTemplate")
    private MongoTemplate thirdMongoTemplate;

    @Autowired
    @Qualifier("forthMongoTemplate")
    private MongoTemplate forthMongoTemplate;

    @Test
    public void findTest() {
        List<User> all = primaryMongoTemplate.findAll(User.class);
        log.info(all.toString());

        List<User> all1 = secondMongoTemplate.findAll(User.class);
        log.info(all1.toString());

    }

    @Test
    public void findTest1() {
        List<User> all = thirdMongoTemplate.findAll(User.class);
        log.info(all.toString());

        List<User> all1 = forthMongoTemplate.findAll(User.class);
        log.info(all1.toString());

    }

}
