package com.ljx.chapter5.mapper;

import com.ljx.chapter5.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BookMyBatisDaoForMapperFactoryBean {

    @Select("select * from t_book where id = #{id}")
    Book findBookById(@Param("id") Integer id) ;

}
