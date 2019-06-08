package com.ljx.chapter11.mappers;

import com.ljx.chapter11.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookMapper {

    Book findBookById(Long id);

}
