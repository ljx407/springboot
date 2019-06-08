//package com.ljx.chapter10springmvc.configuration;
//
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
//
//import javax.servlet.MultipartConfigElement;
//import javax.servlet.ServletRegistration;
//
//public class CustomWebAppInitializationWithAbstractDispatcherServletInitializer extends AbstractDispatcherServletInitializer {
//
//    private final String location = "/usr/local/filetemp";
//    private final Long maxFileSize = 100*1024L;
//    private final Long maxRequestSize = 5*maxFileSize;
//    private final int fileSizeThreshold = 5*100*1024/2;
//
//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//        AnnotationConfigWebApplicationContext mvcConfiguration = new AnnotationConfigWebApplicationContext();
//        mvcConfiguration.register(WebMvcConfiguration.class);
//        return mvcConfiguration;
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/*"};
//    }
//
//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
//        annotationConfigWebApplicationContext.register(RootApplicationConfiguration.class);
//        return annotationConfigWebApplicationContext;
//
//    }
//
//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location,maxFileSize,maxRequestSize,fileSizeThreshold);
//        registration.setMultipartConfig(multipartConfigElement);
//    }
//}
