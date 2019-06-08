package com.ljx.servletupload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
       value = "/hello"
)
public class HelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("xml".equalsIgnoreCase(req.getParameter("type"))) {
            req.getRequestDispatcher("uploadxml.jsp").forward(req,resp);
        } else {
            req.getRequestDispatcher("uploadannotation.jsp").forward(req,resp);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
