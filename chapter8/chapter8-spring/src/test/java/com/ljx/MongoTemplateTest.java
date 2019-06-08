package com.ljx;

import com.ljx.configuration.MongoDBConfiguration;
import com.ljx.entity.Employee;
import com.ljx.entity.User;
import com.mongodb.client.result.DeleteResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RunWith(JUnit4.class)
public class MongoTemplateTest {

    private MongoTemplate mongoTemplate = null ;

    @Before
    public void beforeMethod() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MongoDBConfiguration.class);
        mongoTemplate = annotationConfigApplicationContext.getBean(MongoTemplate.class);
    }

    @Test
    public void testInsertEmployee() {
        mongoTemplate.dropCollection(Employee.class);

        Employee jasonliu = Employee.builder().userName("jasonliu").build();
        mongoTemplate.insert(jasonliu);
    }

    @Test
    public void testInsert() {
        User test99 = User.builder().name("test99").age(10).build();
        mongoTemplate.insert(test99);

    }

    @Test
    public void testDelete() {
        DeleteResult remove = mongoTemplate.remove(query(where("name").is("test99")),User.class);
        System.out.println(remove.getDeletedCount());
    }

    @Test
    public void testUpdate() {
        Update update = new Update();
        update.inc("age",1);
        mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("test99")), update , User.class);
    }

    @Test
    public void testFindAll() {
        List<User> all = mongoTemplate.findAll(User.class);
        all.forEach(item -> System.out.println(item.toString()));
    }

    @Test
    public void testFind() {
        List<User> all = mongoTemplate.find(Query.query(Criteria.where("name").regex("test")),User.class);
        all.forEach(item -> System.out.println(item.toString()));
    }

    @Test
    public void testFind1() {
        List<User> all = mongoTemplate.find(Query.query(Criteria.where("name").regex("test").and("age").gt(4)),User.class);
        all.forEach(item -> System.out.println(item.toString()));
    }
}
