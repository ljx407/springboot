package com.ljx.chapter4.service;

import com.ljx.chapter4.model.Book;

public interface BookSlefService {
    default void init() {
        add(Book.builder().title("springboot in action").author("abc").build());
        add(Book.builder().title("springboot").author("efg").build());
    }
    BookSlefService getInstance();
    Book findByTitle(String title);
    <E extends Book> void add(E book) ;
}
