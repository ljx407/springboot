package com.ljx.chapter10springmvc.configuration;

import com.ljx.chapter10springmvc.filters.CustomerFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

public class CustomWebAppInitializationWithAbstractAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final String location = "/usr/local/filetemp";
    private final Long maxFileSize = 100*1024L;
    private final Long maxRequestSize = 5*maxFileSize;
    private final int fileSizeThreshold = 5*100*1024/2;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic customFilter = servletContext.addFilter("customFilter", CustomerFilter.class);
        customFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/hello/");
        super.onStartup(servletContext);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootApplicationConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location,maxFileSize,maxRequestSize,fileSizeThreshold);
        registration.setMultipartConfig(multipartConfigElement);
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }


}
