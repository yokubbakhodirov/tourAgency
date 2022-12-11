package com.company.tourAgency.command.impl.tour;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.entity.Tour;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.TourServiceImpl;
import com.company.tourAgency.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.TOURS;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;

public class ToursCommand implements Command {
    private static final UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final TourServiceImpl tourService = TourServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);
        Integer userId = user.getId();
        String page = TOURS;

        try {
            if (!user.isLoyal()) {
                Map<Tour, Integer> toursByUserId = tourService.findTourByUserId(userId);
                if (toursByUserId.size() >= 3) {
                    user.setLoyal(true);
                    userService.updateLoyal(true, userId);
                }
            }
            List<Tour> tours = tourService.findAll();
            if (!tours.isEmpty()) {
                request.setAttribute(REQUEST_ATTRIBUTE_TOURS, tours);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);
        return new Router(page, FORWARD);
    }
}
