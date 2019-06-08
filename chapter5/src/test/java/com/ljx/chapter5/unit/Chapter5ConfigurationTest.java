package com.ljx.chapter5.unit;

import com.ljx.chapter5.configuration.Chapter5Configuration;
import com.ljx.chapter5.mapper.BookMapper;
import com.ljx.chapter5.model.Book;
import com.ljx.chapter5.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Chapter5ConfigurationTest {

    private AnnotationConfigApplicationContext annotationConfigApplicationContext ;

    @Before
    public void before() {
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Chapter5Configuration.class);
    }

    @Test
    public void testService() {
        BookService bookService = annotationConfigApplicationContext.getBean("bookServiceForBeanMapperFactory", BookService.class);
        Book book = bookService.getBookById(1);
        Assert.assertNotNull(book);
        Assert.assertEquals(book.getName(),"springboot");
        Assert.assertEquals(book.getMemo(),"simple");
    }

    @Test
    public void testBookMyBatisDao() {
        BookMapper bookMapper = annotationConfigApplicationContext.getBean(BookMapper.class);
        Book book = bookMapper.getBookById(1);
        Assert.assertNotNull(book);
        Assert.assertEquals(book.getName(),"springboot");
        Assert.assertEquals(book.getMemo(),"simple");
    }
}
