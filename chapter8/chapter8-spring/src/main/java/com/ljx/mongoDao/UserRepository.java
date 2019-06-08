package com.ljx.mongoDao;

import com.ljx.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    User findByName(String name);

    List<User> findByNameLikeAndAgeBetween(String name , int begin, int end);

    @Query("{'age' : {$gte : ?0}}")
    List<User> findByCondition(int age) ;

//    List<User> findUserWithNameAndAge(String name, int age);

}
