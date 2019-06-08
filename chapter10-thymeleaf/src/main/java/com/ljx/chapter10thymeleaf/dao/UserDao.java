package com.ljx.chapter10thymeleaf.dao;

import com.ljx.chapter10thymeleaf.model.User;
import com.ljx.chapter10thymeleaf.typehandler.SexEnumTypeHandler;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    @Results({
            @Result(column = "user_name",property = "userName"),
            @Result(column = "sex", property = "sex", typeHandler = SexEnumTypeHandler.class)
    })
    @Select("select * from t_user")
    List<User> findAll();

    @Results({
            @Result(column = "user_name",property = "userName"),
            @Result(column = "sex",property = "sex",typeHandler = SexEnumTypeHandler.class)
    })
    @Select("select * from t_user where id = #{id}")
    User findById(@Param("id") Long id);

    @Insert("insert into t_user(user_name,sex,memo) values(#{userName},#{sex},#{memo})")
    User insertUser(User user);

    @Update("update t_user set user_name = #{userName}, sex = #{sex}, memo=#{memo}")
    User updateUser(User user);

    @Delete("delete from t_user where id = #{id}")
    void deleteUser(@Param("id") Long id);
}
