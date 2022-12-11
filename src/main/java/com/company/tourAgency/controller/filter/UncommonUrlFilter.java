package com.company.tourAgency.controller.filter;

import com.company.tourAgency.command.CommandType;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.company.tourAgency.controller.navigation.PageURL.BASE_URL;

/**
 * Filter for detecting urls pointing to resources files and redirecting them to home page.
 */
@WebFilter(filterName = "UncommonUrlFilter",
        urlPatterns = {"/view/fragment/*", "/view/error/*"})
public class UncommonUrlFilter implements Filter {
    private static final String DEFAULT_PAGE = String.format(BASE_URL, CommandType.DEFAULT.name());
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.sendRedirect(request.getContextPath() + DEFAULT_PAGE);
    }
}
