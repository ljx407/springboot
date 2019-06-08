package com.ljx.chapter7cache.dao;

import com.ljx.chapter7cache.entity.User;
import com.ljx.chapter7cache.enums.SexEnum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUserById(@Param("id") Long id);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(Long id);
    User getUserByName(@Param("name") String name);

    @Insert("insert into t_user(id, name ,memo, sex) values (#{id}, #{name}, #{memo}, #{sex})")
    int insertUserWithProperties(@Param("id") Long id, @Param("name") String name, @Param("memo") String memo, @Param("sex") SexEnum sex);
}
