package com.ljx.chapter4.controller;

import com.ljx.chapter4.command.FrontCommand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req,resp);

        try {
            FrontCommand concreteFrondCommand = Class.forName(String.format("com.ljx.chapter4.command.%sCommand", req.getParameter("Command"))).asSubclass(FrontCommand.class).newInstance();
            concreteFrondCommand.init(req.getServletContext(),req,resp);
            concreteFrondCommand.proceed();
        } catch (ClassNotFoundException e) {
            log.error("未找到对应的处理command",e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

}
