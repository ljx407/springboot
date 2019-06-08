package com.ljx.chapter11.mappers;

import com.ljx.chapter11.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnnotationUserMapper {

    @Select("select * from t_user where id = #{id}")
    User findUserById(@Param("id") Long id);

    @Select("select * from t_user where username like concat('%',#{userName},'%')")
    List<User> findUserByName(@Param("userName") String userName);

    @Insert("insert into t_user(username,memo) values(#{userName},#{memo})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertUser(User user);
}
