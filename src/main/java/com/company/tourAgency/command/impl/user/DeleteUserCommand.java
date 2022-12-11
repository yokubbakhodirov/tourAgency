package com.company.tourAgency.command.impl.user;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.PARAMETER_ID;
import static com.company.tourAgency.controller.navigation.PageURL.HOME;
import static com.company.tourAgency.controller.navigation.Router.Type.REDIRECT;

public class DeleteUserCommand implements Command {
    private static final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        int userId = Integer.parseInt(request.getParameter(PARAMETER_ID));
        try {
            userService.delete(userId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(HOME, REDIRECT);
    }
}
