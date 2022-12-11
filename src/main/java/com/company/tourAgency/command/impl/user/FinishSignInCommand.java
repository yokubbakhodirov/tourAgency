package com.company.tourAgency.command.impl.user;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.entity.enums.UserRole;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.UserServiceImpl;
import com.company.tourAgency.utils.validator.impl.SignInFormValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.*;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;
import static com.company.tourAgency.controller.navigation.Router.Type.REDIRECT;

public class FinishSignInCommand implements Command {
    private static final UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final SignInFormValidator validator = SignInFormValidator.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String page = SIGN_IN;
        Router.Type routerType =  FORWARD;
        HttpSession session = request.getSession();

        Map<String, String[]> requestParameters = request.getParameterMap();
        Map<String, String> validationErrors = validator.validate(requestParameters);

        if (validationErrors.isEmpty()) {
            String email = request.getParameter(PARAMETER_EMAIL);
            String password = request.getParameter(PARAMETER_PASSWORD);

            try {
                Optional<User> user = userService.authenticate(email, password);

                if (user.isPresent()) {
                    session.setAttribute(SESSION_ATTRIBUTE_USER, user.get());
                    if (user.get().getRole().equals(UserRole.ADMIN)) {
                        page = ADMIN;
                    } else {
                        page = HOME;
                    }
                    routerType = REDIRECT;
                } else {
                    request.setAttribute(REQUEST_ATTRIBUTE_USER_INVALID, "");
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            request.setAttribute(REQUEST_ATTRIBUTE_FORM_INVALID, validationErrors);
        }

        return new Router(page, routerType);
    }
}
