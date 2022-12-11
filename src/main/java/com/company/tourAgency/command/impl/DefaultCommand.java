package com.company.tourAgency.command.impl;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.entity.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.SESSION_ATTRIBUTE_CURRENT_PAGE;
import static com.company.tourAgency.controller.navigation.AttributeParameterKey.SESSION_ATTRIBUTE_USER;
import static com.company.tourAgency.controller.navigation.PageURL.*;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = DEFAULT;
        User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);
        if (user.getRole().equals(UserRole.ADMIN)) {
            page = ADMIN;
        }

        session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);
        return new Router(page, FORWARD);
    }
}
