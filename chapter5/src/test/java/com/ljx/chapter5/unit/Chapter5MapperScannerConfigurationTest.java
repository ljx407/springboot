package com.ljx.chapter5.unit;

import com.ljx.chapter5.configuration.Chapter5MapperScannerConfiguration;
import com.ljx.chapter5.mapper.BookMapper;
import com.ljx.chapter5.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
public class Chapter5MapperScannerConfigurationTest {

    private AnnotationConfigApplicationContext annotationConfigApplicationContext ;

    @Before
    public void before() {
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Chapter5MapperScannerConfiguration.class);
    }

    @Test
    public void testRoleDao() {
        BookMapper bean = annotationConfigApplicationContext.getBean(BookMapper.class);
        Book book = bean.getBookById(1);
        Assert.assertNotNull(book);
        Assert.assertEquals(book.getName(),"springboot");

    }
}
