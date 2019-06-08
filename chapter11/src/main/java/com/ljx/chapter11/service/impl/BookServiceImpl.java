package com.ljx.chapter11.service.impl;

import com.ljx.chapter11.mappers.BookMapper;
import com.ljx.chapter11.model.Book;
import com.ljx.chapter11.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper ;

    @Override
    public Book findBookById(Long id) {
        return bookMapper.findBookById(id);
    }
}
