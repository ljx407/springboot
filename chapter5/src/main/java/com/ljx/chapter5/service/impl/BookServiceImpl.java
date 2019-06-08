package com.ljx.chapter5.service.impl;

import com.ljx.chapter5.mapper.BookMapper;
import com.ljx.chapter5.model.Book;
import com.ljx.chapter5.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public List<Book> findBookByCondition(String name) {
        return bookMapper.findBookByCondition(name);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public int insertBook(Book book) {
        return bookMapper.insertBook(book);
    }

    @Override
    public int deleteBook(Book book) {
        return bookMapper.deleteBook(book);
    }
}
