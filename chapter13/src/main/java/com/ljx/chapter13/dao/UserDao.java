package com.ljx.chapter13.dao;

import com.ljx.chapter13.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    @Select("select * from t_user")
    @Results(
            @Result(column = "user_name", property = "userName")
    )
    List<User> findAllUser();


}
