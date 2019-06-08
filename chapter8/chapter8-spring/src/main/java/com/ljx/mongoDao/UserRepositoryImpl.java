package com.ljx.mongoDao;

import com.ljx.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate ;

    List<User> findUserWithNameAndAge(String name ,int age) {
        log.info("####findUserWithNameAndAge invoke");
        List<User> users = mongoTemplate.find(Query.query(Criteria.where("name").regex(name).and("age").gt(age)), User.class);
        return users ;
    }

}
