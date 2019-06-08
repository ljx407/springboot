package com.ljx;

import com.ljx.configuration.MongoDBConfiguration;
import com.ljx.entity.User;
import com.ljx.mongoDao.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Slf4j
@RunWith(JUnit4.class)
public class MongoRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void beforeMethod() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MongoDBConfiguration.class);
        userRepository = context.getBean(UserRepository.class);
    }

    @Test
    public void insert() {
        User test98 = User.builder().name("test98").age(18).build();
        userRepository.insert(test98);
    }

    @Test
    public void delete() {
        User test98 = userRepository.findByName("test98");
        userRepository.deleteById(test98.getId());
    }

    @Test
    public void delete1() {
        insert();
        User test98 = userRepository.findByName("test98");
        userRepository.delete(test98);
    }

    @Test
    public void update() {
        User test98 = userRepository.findByName("test98");
        test98.setAge(19);
        userRepository.save(test98);
    }

    @Test
    public void find() {
        User test98 = userRepository.findByName("test98");
        log.info(test98.toString());
    }

    @Test
    public void findById() {
        User test98 = userRepository.findById("5ca9ae4b5565b0b4375063ae").orElse(null);
        log.info(test98 != null ? test98.toString() : "-1");
    }

    @Test
    public void findAll() {
        List<User> all = userRepository.findAll();
        log.info(all.toString());
    }

    @Test
    public void findAllByPage() {

        Page<User> all = userRepository.findAll(PageRequest.of(0, 2));
        int totalPages = all.getTotalPages();
        log.info("###totalPages: {}", totalPages);
        long totalElements = all.getTotalElements();
        log.info("### totalElements: {}" ,totalElements);
        log.info(all.getContent().toString());
    }

    @Test
    public void findByCondition() {
        List<User> test98 = userRepository.findByNameLikeAndAgeBetween("test", 1, 16);
        log.info(test98.toString());
    }

    @Test
    public void findByCondition1() {
        List<User> test98 = userRepository.findByCondition(5);
        log.info(test98.toString());
    }

//    @Test
//    public void findByCondition2() {
//        List<User> test98 = userRepository.findUserWithNameAndAge("test",5);
//        log.info(test98.toString());
//    }
}
