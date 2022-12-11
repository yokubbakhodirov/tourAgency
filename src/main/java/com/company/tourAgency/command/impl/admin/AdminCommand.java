package com.company.tourAgency.command.impl.admin;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.entity.Order;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.OrderServiceImpl;
import com.company.tourAgency.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.ADMIN;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;

public class AdminCommand implements Command {
    private static final UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final OrderServiceImpl orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = ADMIN;

        if (request.getParameter(REQUEST_ATTRIBUTE_ADMIN_DATA) != null) {
            String requestParameter = request.getParameter(REQUEST_ATTRIBUTE_ADMIN_DATA);

            if (requestParameter.equalsIgnoreCase(REQUEST_ATTRIBUTE_PARAMETER_USERS)) {
                try {
                    List<User> users = userService.findAll();
                    request.setAttribute(REQUEST_ATTRIBUTE_PARAMETER_USERS, users);
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }
            } else if (requestParameter.equalsIgnoreCase(REQUEST_ATTRIBUTE_PARAMETER_ORDERS)) {
                try {
                    List<Order> orders = orderService.findAll();
                    request.setAttribute(REQUEST_ATTRIBUTE_PARAMETER_ORDERS, orders);
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }
            }
        }

        session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);
        return new Router(page, FORWARD);
    }
}
