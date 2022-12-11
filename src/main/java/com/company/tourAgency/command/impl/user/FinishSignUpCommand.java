package com.company.tourAgency.command.impl.user;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.UserServiceImpl;
import com.company.tourAgency.utils.validator.impl.SignUpFormValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.SIGN_IN;
import static com.company.tourAgency.controller.navigation.PageURL.SIGN_UP;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;
import static com.company.tourAgency.controller.navigation.Router.Type.REDIRECT;

public class FinishSignUpCommand implements Command {
    private static final UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final SignUpFormValidator validator = SignUpFormValidator.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String page = SIGN_UP;
        Router.Type routerType =  FORWARD;

        Map<String, String[]> requestParameters = request.getParameterMap();
        Map<String, String> validationErrors = validator.validate(requestParameters);

        if (validationErrors.isEmpty()) {
            String email = request.getParameter(PARAMETER_EMAIL);
            String password = request.getParameter(PARAMETER_PASSWORD);
            String name = request.getParameter(PARAMETER_NAME);
            String lastname = request.getParameter(PARAMETER_LASTNAME);
            String phone = String.valueOf(request.getParameter(PARAMETER_PHONE));

            try {
                if (userService.signUp(email, password, name, lastname, phone)) {
                    page = SIGN_IN;
                    routerType = REDIRECT;
                } else {
                    request.setAttribute(REQUEST_ATTRIBUTE_USER_EXISTS, "");
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
