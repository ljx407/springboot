package com.ljx.chapter7.dao;

import com.ljx.chapter7.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    @Select("select * from t_user where id = #{id}")
    User getUserById(@Param("id") Long id);
}
