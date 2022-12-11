package com.company.tourAgency.command.impl.tour;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.entity.Tour;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.TourServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.REQUEST_ATTRIBUTE_TOURS;
import static com.company.tourAgency.controller.navigation.AttributeParameterKey.SESSION_ATTRIBUTE_CURRENT_PAGE;
import static com.company.tourAgency.controller.navigation.PageURL.REST;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;

public class RestCommand implements Command {
    private static final TourServiceImpl tourService = TourServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = REST;

        try {
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
