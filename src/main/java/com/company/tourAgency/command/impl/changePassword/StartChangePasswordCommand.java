package com.company.tourAgency.command.impl.changePassword;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.*;
import static com.company.tourAgency.controller.navigation.Router.Type.*;

public class StartChangePasswordCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = CHANGE_PASSWORD;
        Router.Type routerType = FORWARD;

        User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);
        boolean enableChange = switch (user.getRole()) {
            case ADMIN, USER -> true;
            default -> {
                String email = (String) session.getAttribute(SESSION_ATTRIBUTE_EMAIL);
                String keySent = (String) session.getAttribute(SESSION_ATTRIBUTE_KEY_SENT);
                String keyReceived = (String) session.getAttribute(SESSION_ATTRIBUTE_KEY_RECEIVED);
                yield email != null
                        && keySent != null
                        && keySent.equals(keyReceived);
            }
        };

        if (enableChange) {
            session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);
        } else {
            page = HOME;
            routerType = REDIRECT;
        }

        return new Router(page, routerType);
    }
}
