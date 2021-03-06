package com.ljx.chapter4.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class MyCharacterEncodeingFilterBaseConfiguration implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("####### MyCharacterEncodeingFilterBaseConfiguration.doFilter before {}",((HttpServletRequest)request).getRequestURL());
        chain.doFilter(request,response);
        log.info("####### MyCharacterEncodeingFilterBaseConfiguration.doFilter after {}",((HttpServletRequest)request).getRequestURL());

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("####### MyCharacterEncodeingFilterBaseConfiguration.init invoke!");
    }

    @Override
    public void destroy() {
        log.info("####### MyCharacterEncodeingFilterBaseConfiguration.destroy invoke!");
    }
}
