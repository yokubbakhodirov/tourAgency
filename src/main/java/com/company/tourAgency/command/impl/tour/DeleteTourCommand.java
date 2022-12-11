package com.company.tourAgency.command.impl.tour;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.TourService;
import com.company.tourAgency.service.impl.TourServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.PARAMETER_TOUR_ID;
import static com.company.tourAgency.controller.navigation.PageURL.TOURS;
import static com.company.tourAgency.controller.navigation.Router.Type.REDIRECT;

public class DeleteTourCommand implements Command {
    private static final TourService tourService = TourServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        int tourId = Integer.parseInt(request.getParameter(PARAMETER_TOUR_ID));

        try {
            tourService.delete(tourId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(TOURS, REDIRECT);
    }
}
