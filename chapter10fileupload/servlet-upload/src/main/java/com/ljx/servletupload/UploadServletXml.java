package com.ljx.servletupload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class UploadServletXml extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part uploadfile = req.getPart("uploadfile");
        String submittedFileName = uploadfile.getSubmittedFileName();
        uploadfile.write(submittedFileName);
        req.getRequestDispatcher("/uploadSuccess.jsp").forward(req,resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
