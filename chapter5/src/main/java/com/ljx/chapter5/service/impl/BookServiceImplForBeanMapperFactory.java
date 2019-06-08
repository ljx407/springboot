package com.ljx.chapter5.service.impl;

import com.ljx.chapter5.mapper.BookMyBatisDaoForMapperFactoryBean;
import com.ljx.chapter5.model.Book;
import com.ljx.chapter5.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BookServiceImplForBeanMapperFactory implements BookService {

    @Autowired
    private BookMyBatisDaoForMapperFactoryBean bookMyBatisDaoForMapperFactoryBean;

    @Override
    public Book getBookById(Integer id) {
        return bookMyBatisDaoForMapperFactoryBean.findBookById(id);
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findBookByCondition(String name) {
        return null;
    }

    @Override
    public int updateBook(Book book) {
        return 0;
    }

    @Override
    public int insertBook(Book book) {
        return 0;
    }

    @Override
    public int deleteBook(Book book) {
        return 0;
    }
}
