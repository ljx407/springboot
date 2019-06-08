package com.ljx.chapter9react.mapper;


import com.ljx.chapter9react.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper extends JpaRepository<User,Long> {

}
