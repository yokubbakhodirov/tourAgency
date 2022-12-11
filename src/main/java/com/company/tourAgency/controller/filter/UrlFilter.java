package com.company.tourAgency.controller.filter;

import com.company.tourAgency.command.CommandType;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.company.tourAgency.controller.navigation.PageURL.*;

/**
 * Filter for redirecting jsp page requests to corresponding commands.
 */
@WebFilter(filterName = "UrlFilter", urlPatterns = "/view/page/*")
public class
UrlFilter implements Filter {
    private static Map<String, String> urls;

    public void init(FilterConfig config) throws ServletException {
        urls = new HashMap<>();
        urls.put(HOME, String.format(BASE_URL, CommandType.HOME.name()));
        urls.put(DEFAULT, String.format(BASE_URL, CommandType.DEFAULT.name()));

        urls.put(SIGN_IN, String.format(BASE_URL, CommandType.SIGN_IN.name()));
        urls.put(SIGN_UP, String.format(BASE_URL, CommandType.SIGN_UP.name()));

        urls.put(ABOUT_US, String.format(BASE_URL, CommandType.ABOUT_US.name()));
        urls.put(TOURS, String.format(BASE_URL, CommandType.TOURS.name()));
        urls.put(MY_TOURS, String.format(BASE_URL, CommandType.MY_TOURS.name()));
        urls.put(REST, String.format(BASE_URL, CommandType.REST.name()));
        urls.put(EXCURSION, String.format(BASE_URL, CommandType.EXCURSION.name()));
        urls.put(SHOPPING, String.format(BASE_URL, CommandType.SHOPPING.name()));
        urls.put(BUY_PAGE, String.format(BASE_URL, CommandType.BUY.name()));
        urls.put(ADD_TOURS, String.format(BASE_URL, CommandType.ADD_TOURS.name()));
        urls.put(ADMIN, String.format(BASE_URL, CommandType.ADMIN.name()));

        urls.put(CHANGE_PASSWORD, String.format(BASE_URL, CommandType.CHANGE_PASSWORD.name()));
        urls.put(SEND_KEY, String.format(BASE_URL, CommandType.SEND_KEY.name()));
        urls.put(CONFIRM_KEY, String.format(BASE_URL, CommandType.CONFIRM_KEY.name()));

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String servletPath = request.getServletPath();
        if (urls.containsKey(servletPath)) {
            response.sendRedirect(request.getContextPath() + urls.get(servletPath));
        } else {
            chain.doFilter(req, res);
        }
    }
}
