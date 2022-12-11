package com.company.tourAgency.command.impl.aboutUs;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.controller.navigation.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.SESSION_ATTRIBUTE_CURRENT_PAGE;
import static com.company.tourAgency.controller.navigation.PageURL.ABOUT_US;

public class AboutUsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = ABOUT_US;

        session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);
        return new Router(page, Router.Type.FORWARD);
    }
}
