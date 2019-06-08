package com.ljx.chapter4.service.impl;

import com.ljx.chapter4.model.Book;
import com.ljx.chapter4.service.BookSlefService;

import java.util.ArrayList;
import java.util.List;

public class BookSlefServiceImpl implements BookSlefService {
    private List<Book> bookRepository = new ArrayList<>();
    @Override
    public BookSlefService getInstance() {
        this.init();
        return this;
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.stream()
                .filter(item -> title.equals(item.getTitle()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public <E extends Book> void add(E book) {
        bookRepository.add(book);
    }
}
