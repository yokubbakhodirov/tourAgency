package com.company.tourAgency.command.impl.localization;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.Router.Type.REDIRECT;

public class ChangeLocalization implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String localization = request.getParameter(REQUEST_PARAMETER_LOCALIZATION);
        session.setAttribute(SESSION_ATTRIBUTE_LOCALIZATION, localization);

        String page = (String) session.getAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE);
        return new Router(page, REDIRECT);
    }
}
