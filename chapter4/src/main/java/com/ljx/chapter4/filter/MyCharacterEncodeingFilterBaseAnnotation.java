package com.ljx.chapter4.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
//@Component
@WebFilter(urlPatterns = {"/*"},filterName = "myCharacterEncodeingFilterBaseAnnotation")
@Order(2)
public class MyCharacterEncodeingFilterBaseAnnotation implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("####### MyCharacterEncodeingFilterBaseAnnotation.doFilter before {}",((HttpServletRequest)request).getRequestURL());
        chain.doFilter(request,response);
        log.info("####### MyCharacterEncodeingFilterBaseAnnotation.doFilter after {}",((HttpServletRequest)request).getRequestURL());

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("####### MyCharacterEncodeingFilterBaseAnnotation.init invoke!");
    }

    @Override
    public void destroy() {
        log.info("####### MyCharacterEncodeingFilterBaseAnnotation.destroy invoke!");
    }
}
