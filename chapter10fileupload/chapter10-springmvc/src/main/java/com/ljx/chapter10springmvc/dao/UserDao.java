package com.ljx.chapter10springmvc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao  {

    String findUserById(Long id);
}
