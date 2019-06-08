package com.ljx.chapter4.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {
    ServletContext servletContext ;
    HttpServletRequest httpServletRequest ;
    HttpServletResponse httpServletResponse ;
    public void init(ServletContext servletContext,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.servletContext = servletContext;
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;

    }
    public abstract void proceed() throws ServletException, IOException ;
    public void forward(String target) throws ServletException, IOException {
        target = String.format("/WEB-INF/jsp/%s.jsp", target);
        servletContext.getRequestDispatcher(target).forward(httpServletRequest,httpServletResponse);
    }
}
