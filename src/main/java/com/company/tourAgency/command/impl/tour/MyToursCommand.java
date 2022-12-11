package com.company.tourAgency.command.impl.tour;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.entity.Tour;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.TourServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.MY_TOURS;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;

public class MyToursCommand implements Command {
    private static final TourServiceImpl tourService = TourServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = MY_TOURS;
        int userId = ((User) session.getAttribute(SESSION_ATTRIBUTE_USER)).getId();

        try {
            Map<Tour, Integer> tours = tourService.findTourByUserId(userId);
            request.setAttribute(REQUEST_ATTRIBUTE_MY_TOURS, tours);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);
        return new Router(page, FORWARD);
    }
}
