package com.ljx.chapter9react.dao;

import com.ljx.chapter9react.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {

    @Select("select * from t_user where user_name like concat('%',#{userName},'%')")
    User findUserByCondition(@Param("userName") String userName);
}
