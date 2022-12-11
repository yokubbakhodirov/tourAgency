package com.company.tourAgency.command.impl.changePassword;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.*;
import static com.company.tourAgency.controller.navigation.Router.Type.*;

public class StartConfirmKeyCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = CONFIRM_KEY;
        Router.Type routerType = FORWARD;

        if (session.getAttribute(SESSION_ATTRIBUTE_EMAIL) != null
                && session.getAttribute(SESSION_ATTRIBUTE_KEY_SENT) != null) {
            session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);
        } else {
            page = HOME;
            routerType = REDIRECT;
        }

        return new Router(page, routerType);
    }
}
