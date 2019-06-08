//package com.ljx.chapter10springmvc.configuration;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.MultipartConfigElement;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//public class CustomWebAppInitialization implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//        AnnotationConfigWebApplicationContext rootApplication = new AnnotationConfigWebApplicationContext();
//        rootApplication.register(RootApplicationConfiguration.class);
//
//        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootApplication);
//        servletContext.addListener(contextLoaderListener);
//
////        LogbackConfigListener logbackConfigListener = new LogbackConfigListener();
////        servletContext.addListener(logbackConfigListener);
////        servletContext.setInitParameter("logbackConfigLocation", "classpath:logback.xml");
//
//        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
//        rootApplication.register(WebMvcConfiguration.class);
//
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
//        ServletRegistration.Dynamic dispatcherServletRegistration = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
//        dispatcherServletRegistration.setLoadOnStartup(1);
//        dispatcherServletRegistration.addMapping("/*");
//
//        final String location = "/usr/local/filetemp";
//        final Long maxFileSize = 100*1024L;
//        final Long maxRequestSize = 5*maxFileSize;
//        final int fileSizeThreshold = 5*100*1024/2;
//        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location,maxFileSize,maxRequestSize,fileSizeThreshold);
//        dispatcherServletRegistration.setMultipartConfig(multipartConfigElement);
//
//    }
//}
