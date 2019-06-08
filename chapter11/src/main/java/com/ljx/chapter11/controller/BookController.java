package com.ljx.chapter11.controller;

import com.ljx.chapter11.model.Book;
import com.ljx.chapter11.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService ;

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }
}
