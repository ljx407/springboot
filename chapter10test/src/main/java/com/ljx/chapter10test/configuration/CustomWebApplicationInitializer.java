package com.ljx.chapter10test.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class CustomWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

//        LogbackConfigListener logbackConfigListener = new LogbackConfigListener();
//        servletContext.addListener(logbackConfigListener);
//        servletContext.setInitParameter("logbackConfigLocation", "classpath:logback.xml");

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfiguration.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic dispatcherRegistration = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        dispatcherRegistration.addMapping("/");
        dispatcherRegistration.setLoadOnStartup(1);



    }
}
