package com.company.tourAgency.command.impl.changePassword;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.entity.enums.UserRole;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.UserServiceImpl;
import com.company.tourAgency.utils.validator.impl.ChangePasswordFormValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.*;
import static com.company.tourAgency.controller.navigation.Router.Type.*;

public class FinishChangePasswordCommand implements Command {
    private static final ChangePasswordFormValidator validator = ChangePasswordFormValidator.getInstance();
    private static final UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = CHANGE_PASSWORD;
        Router.Type routerType = FORWARD;

        User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);

        boolean enableChange = true;
        if (user == null || user.getRole() == UserRole.GUEST) {

            String email = (String) session.getAttribute(SESSION_ATTRIBUTE_EMAIL);
            String keySent =  (String)  session.getAttribute(SESSION_ATTRIBUTE_KEY_SENT);
            String keyReceived = (String) session.getAttribute(SESSION_ATTRIBUTE_KEY_RECEIVED);

            if (email == null || keySent == null || keyReceived == null || !keySent.equals(keyReceived)) {
                enableChange = false;
            }
        }

        if (enableChange) {
            Map<String, String[]> requestParameters = request.getParameterMap();
            Map<String, String> validationErrors = validator.validate(requestParameters);

            if (validationErrors.isEmpty()) {
                String password = request.getParameter(PARAMETER_PASSWORD);

                try {
                    String email = switch (user.getRole()) {
                        case ADMIN, USER -> user.getEmail();
                        default -> (String) session.getAttribute(SESSION_ATTRIBUTE_EMAIL);
                    };

                    service.updatePassword(email, password);

                    session.invalidate();
                    page = SIGN_IN;
                    routerType = REDIRECT;

                } catch (ServiceException e) {
                    throw new CommandException(e.getMessage(), e);
                }
            } else {
                request.setAttribute(REQUEST_ATTRIBUTE_FORM_INVALID, validationErrors);
            }
        } else {
            page = HOME;
            routerType = REDIRECT;
        }

        return new Router(page, routerType);
    }
}
