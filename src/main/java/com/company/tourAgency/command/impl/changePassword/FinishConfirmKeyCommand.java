package com.company.tourAgency.command.impl.changePassword;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.*;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;
import static com.company.tourAgency.controller.navigation.Router.Type.REDIRECT;

public class FinishConfirmKeyCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = CONFIRM_KEY;
        Router.Type routerType = FORWARD;

        String receivedKeyStr = request.getParameter(PARAMETER_RECEIVED_KEY);
        String keySent = (String) session.getAttribute(SESSION_ATTRIBUTE_KEY_SENT);

        if (session.getAttribute(SESSION_ATTRIBUTE_EMAIL) != null
                && keySent != null
                && receivedKeyStr != null) {

            if (receivedKeyStr.equals(keySent)) {
                session.setAttribute(SESSION_ATTRIBUTE_KEY_RECEIVED, receivedKeyStr);

                page = CHANGE_PASSWORD;
                session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);
            } else {
                request.setAttribute(REQUEST_ATTRIBUTE_CONFIRM_KEY_INVALID, "");
            }

        } else {
            page = HOME;
            routerType = REDIRECT;
        }


        return new Router(page, routerType);
    }
}
