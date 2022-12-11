package com.company.tourAgency.command.impl.tour.addTours;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.TourServiceImpl;
import com.company.tourAgency.utils.validator.impl.TourFormValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.ADD_TOURS;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;

public class FinishAddTours implements Command {
    private static final TourServiceImpl tourService = TourServiceImpl.getInstance();
    private static final TourFormValidator validator = TourFormValidator.getInstance();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = ADD_TOURS;

        Map<String, String[]> requestParameters = request.getParameterMap();
        Map<String, String> validationErrors = validator.validate(requestParameters);

        if (validationErrors.isEmpty()) {
            String name = request.getParameter(PARAMETER_NAME);
            String type = request.getParameter(PARAMETER_TYPE);
            String description = request.getParameter(PARAMETER_DESCRIPTION);
            Integer discount = Integer.parseInt(request.getParameter(PARAMETER_DISCOUNT));
            String date = request.getParameter(PARAMETER_DATE);
            double price = Double.parseDouble(request.getParameter(PARAMETER_PRICE));
            String imagePath = request.getParameter(PARAMETER_IMAGE_PATH);

            try {
                boolean isInserted = tourService.insert(name, type, description, discount, date, price, imagePath);
                if (isInserted) {
                    request.setAttribute(REQUEST_ATTRIBUTE_ADD_TOUR_SUCCESS, "");
                } else {
                    request.setAttribute(REQUEST_ATTRIBUTE_TOUR_EXISTS, "");
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }

        } else {
            request.setAttribute(REQUEST_ATTRIBUTE_FORM_INVALID, validationErrors);
        }

        session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);
        return new Router(page, FORWARD);
    }
}
