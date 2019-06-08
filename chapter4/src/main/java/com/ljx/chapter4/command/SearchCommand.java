package com.ljx.chapter4.command;

import com.ljx.chapter4.model.Book;
import com.ljx.chapter4.service.impl.BookSlefServiceImpl;

import javax.servlet.ServletException;
import java.io.IOException;

public class SearchCommand extends FrontCommand {

    @Override
    public void proceed() throws ServletException, IOException {
        Book book = new BookSlefServiceImpl().getInstance().findByTitle(httpServletRequest.getParameter("title"));
        if(book == null) {
            forward("book-notfound");
        } else {
            httpServletRequest.setAttribute("book",book);
            forward("book-found");
        }
    }
}