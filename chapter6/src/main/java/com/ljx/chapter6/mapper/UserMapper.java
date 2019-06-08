package com.ljx.chapter6.mapper;

import com.ljx.chapter6.enums.SexEnum;
import com.ljx.chapter6.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Results({
            @Result(column = "user_name",property = "userName")
    })
    @Select("select * from t_user where id = #{id}")
    User getUserById(@Param("id") Long id );

    User findUserById(Long id);

    int insertUser(User user);

    @Delete("delete from t_user where id = #{id}")
    int deleteUserById(@Param("id") Long id);

    @Insert("insert into t_user(id,user_name,note,sex) values(#{id},#{userName},#{note},#{sex})")
    int insertUserWithId(@Param("id") Long id, @Param("userName") String username, @Param("note") String note ,@Param("sex") SexEnum sex);

}
