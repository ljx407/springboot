package com.ljx.chapter9.dao;

import com.ljx.chapter9.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List<User> findAll() ;

    @Select("select * from t_user where id = #{id}")
    @Results(
            @Result(column = "user_name",property = "userName")
    )
    User findById(@Param("id") Long id) ;

    @Select("select * from t_user where user_name like concat('%',#{userName},'%')")
    @Results(
            @Result(column = "user_name",property = "userName")
    )
    List<User> findByUserName(@Param("userName") String userName) ;


    @Delete("delete from t_user where id = #{id}")
    void delete(@Param("id") Long id) ;

    Long insert(User user);

    void updateById(User user);


}
