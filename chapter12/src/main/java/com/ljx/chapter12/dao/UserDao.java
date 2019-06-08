package com.ljx.chapter12.dao;

import com.ljx.chapter12.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    @Select("select * from t_user")
    @Results(
            @Result(column = "user_name", property = "userName")
    )
    List<User> findAll();

    @Select("select * from t_user where id = #{id}")
    @Results(
            @Result(column = "user_name", property = "userName")
    )
    User findUserById(@Param("id") Long id);

    @Select("select * from t_user where user_name = #{username}")
    @Results(
            @Result(column = "user_name", property = "userName")
    )
    User findUserByUsername(@Param("username") String username);

    @Insert("insert into t_user(user_name,pass,sex,memo,available) values(#{userName},#{pass},#{sex},#{memo},#{available})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertUser(User user);

    @Update("update t_user set pass = #{pass} where user_name = #{username}")
    void updatePaasByUserName(@Param("username") String username, @Param("pass") String pass);

    @Update("update t_user set user_name = #{userName}, memo = #{memo}, sex = #{sex}, available = #{available} where id = #{id}")
    void updateUser(User user);
}