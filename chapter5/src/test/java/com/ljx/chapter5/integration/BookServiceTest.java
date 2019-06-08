package com.ljx.chapter5.integration;

import com.ljx.chapter5.enums.BookTypeEnum;
import com.ljx.chapter5.model.Book;
import com.ljx.chapter5.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService ;

    @Test
    public void getBookByIdTest() {
        Book book = bookService.getBookById(1);
        Assert.assertNotNull(book);
        Assert.assertEquals(book.getName(),"springboot");
    }

    @Test
    public void findAllTest() {
        List<Book> books = bookService.findAll();
        Assert.assertNotNull(books);
        Assert.assertEquals(books.size(),3);
    }

    @Test
    public void findBookByConidtionTest() {
        List<Book> books = bookService.findBookByCondition("spring");
        Assert.assertNotNull(books);
        Assert.assertEquals(books.size(),2);
    }

    @Test
    @Transactional
    @Rollback
    public void updateBookTest() {
        Book book = Book.builder().name("springboottest").id(2).build();
        int result = bookService.updateBook(book);
        Assert.assertEquals(result,1);
    }

    @Test
    @Transactional
    @Rollback
    public void insertBookTest() {
        Book book = Book.builder().memo("test").type(BookTypeEnum.NEW).name("springboottest").build();
        int result = bookService.insertBook(book);
        Assert.assertEquals(result,1);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteBookTest() {
        Book book = Book.builder().id(3).build();
        int result = bookService.deleteBook(book);
        Assert.assertEquals(result,1);
    }
}
