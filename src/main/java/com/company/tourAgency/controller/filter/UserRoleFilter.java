package com.company.tourAgency.controller.filter;

import com.company.tourAgency.command.CommandType;
import com.company.tourAgency.controller.navigation.PageURL;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.entity.enums.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Map;

import static com.company.tourAgency.command.CommandType.*;
import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;

/**
 * Filter for checking user role
 */
@WebFilter(filterName = "UserRoleFilter",
        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST},
        urlPatterns = {"/controller"})
public class UserRoleFilter implements Filter {
    private Map<UserRole, EnumSet<CommandType>> commands;

    public void init(FilterConfig config) throws ServletException {
        commands = Map.of(UserRole.ADMIN, EnumSet.of(
                        ABOUT_US,
                        ADMIN,
                        DELETE_ORDER,
                        ADD_TOURS,
                        FINISH_ADD_TOURS,
                        TOURS,
                        EXCURSION,
                        REST,
                        SHOPPING,
                        DELETE_USER,
                        LOGOUT,
                        DEFAULT,
                        HOME,
                        CHANGE_LOCALIZATION,
                        DELETE_TOUR
                ),

                UserRole.USER, EnumSet.of(
                        ABOUT_US,
                        BUY,
                        FINISH_BUY,
                        TOURS,
                        EXCURSION,
                        REST,
                        SHOPPING,
                        MY_TOURS,
                        LOGOUT,
                        DEFAULT,
                        HOME,
                        CHANGE_LOCALIZATION
                ),

                UserRole.GUEST, EnumSet.of(
                        ABOUT_US,
                        SEND_KEY,
                        FINISH_SEND_KEY,
                        CONFIRM_KEY,
                        FINISH_CONFIRM_KEY,
                        CHANGE_PASSWORD,
                        FINISH_CHANGE_PASSWORD,
                        SIGN_IN,
                        FINISH_SIGN_IN,
                        SIGN_UP,
                        FINISH_SIGN_UP,
                        DEFAULT,
                        HOME,
                        CHANGE_LOCALIZATION
                ));
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        String commandStr = servletRequest.getParameter(REQUEST_PARAMETER_COMMAND);

        User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);
        UserRole userRole = user != null ? user.getRole() : UserRole.GUEST;

        EnumSet<CommandType> allowedCommands = commands.get(userRole);
        CommandType commandType = CommandType.defineCommandType(commandStr);

        if (!allowedCommands.contains(commandType)) {
            servletRequest.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, PageURL.DEFAULT);
            servletResponse.sendRedirect(servletRequest.getContextPath() + PageURL.DEFAULT);
        } else {
            chain.doFilter(request, response);
        }
    }
}
