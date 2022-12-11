package com.company.tourAgency.command.impl.user;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.DEFAULT;
import static com.company.tourAgency.controller.navigation.Router.Type.REDIRECT;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        String page = DEFAULT;
        String localisation = (String) session.getAttribute(SESSION_ATTRIBUTE_LOCALIZATION);

        session.invalidate();
        session = request.getSession();

        session.setAttribute(SESSION_ATTRIBUTE_LOCALIZATION, localisation);
        session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);

        return new Router(page, REDIRECT);
    }
}
