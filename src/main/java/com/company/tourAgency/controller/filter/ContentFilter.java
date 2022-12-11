package com.company.tourAgency.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

/**
 * Filter for setting character encoding and content type
 */
@WebFilter(filterName = "ContentFilter", urlPatterns = "/*")
public class ContentFilter implements Filter {

    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(CHARACTER_ENCODING);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        response.setContentType(CONTENT_TYPE);
        chain.doFilter(request, response);
    }
}
