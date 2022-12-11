package com.company.tourAgency.command.impl.changePassword;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.UserServiceImpl;
import com.company.tourAgency.utils.validator.PatternValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.CONFIRM_KEY;
import static com.company.tourAgency.controller.navigation.PageURL.SEND_KEY;
import static com.company.tourAgency.controller.navigation.Router.Type.*;

public class FinishSendKeyCommand implements Command {
    private static final UserServiceImpl service = UserServiceImpl.getInstance();
    private static final PatternValidator validator = PatternValidator.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = SEND_KEY;
        Router.Type routerTyoe = FORWARD;

        String locale = (String) session.getAttribute(SESSION_ATTRIBUTE_LOCALIZATION);

        String email = request.getParameter(PARAMETER_EMAIL);

        if (validator.isEmailValid(email)) {

            try {
                Optional<String> key = service.sendKeyToUpdatePassword(email, locale);
                if (key.isPresent()) {
                    session.setAttribute(SESSION_ATTRIBUTE_EMAIL, email);
                    session.setAttribute(SESSION_ATTRIBUTE_KEY_SENT, key.get());

                    page = CONFIRM_KEY;
                    routerTyoe = REDIRECT;
                    session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);
                } else {
                    request.setAttribute(REQUEST_ATTRIBUTE_FORM_INVALID, Map.of(
                            PARAMETER_EMAIL, ""
                    ));
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            request.setAttribute(REQUEST_ATTRIBUTE_FORM_INVALID, Map.of(
                    PARAMETER_EMAIL, ""
            ));
        }

        return new Router(page, routerTyoe);
    }
}
