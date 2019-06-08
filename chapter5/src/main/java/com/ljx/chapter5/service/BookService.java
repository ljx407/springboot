package com.ljx.chapter5.service;

import com.ljx.chapter5.model.Book;

import java.util.List;

public interface BookService {
    Book getBookById(Integer id) ;

    List<Book> findAll() ;

    List<Book> findBookByCondition(String name);

    int updateBook(Book book);

    int insertBook(Book book);

    int deleteBook(Book book);
}
