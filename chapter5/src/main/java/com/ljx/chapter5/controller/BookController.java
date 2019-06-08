package com.ljx.chapter5.controller;

import com.ljx.chapter5.model.Book;
import com.ljx.chapter5.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService ;

    @GetMapping("/{id}")
    @ResponseBody
    public String getBookById(@PathVariable Integer id) {
        Book book = bookService.getBookById(id);
        return book.toString();
    }
}
