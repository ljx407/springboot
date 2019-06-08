package com.ljx.chapter5.mapper;

import com.ljx.chapter5.model.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {
    Book getBookById(Integer id);

    @Select("select * from t_book")
    List<Book> findAll();

    @Select("select * from t_book where name like concat('%',#{name},'%')")
    List<Book> findBookByCondition(@Param("name") String name);

    @Update("update t_book set name=#{name} where id = #{id}")
    int updateBook(Book book);

    @Insert("insert into t_book(name,type,memo) values(#{name},#{type},#{memo})")
    int insertBook(Book book);

    @Delete("delete from t_book where id = #{id}")
    int deleteBook(Book book);
}
