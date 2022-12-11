package com.company.tourAgency.command.impl.buy;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.entity.Card;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.CardService;
import com.company.tourAgency.service.impl.CardServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.BUY_PAGE;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;

public class StartBuyCommand implements Command {
    private static final CardService cardService = CardServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = BUY_PAGE;

        String tourId = request.getParameter(PARAMETER_ATTRIBUTE_TOUR_ID);
        String tourAmount = request.getParameter(PARAMETER_ATTRIBUTE_TOUR_AMOUNT);
        User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);

        session.setAttribute(PARAMETER_ATTRIBUTE_TOUR_ID, tourId);
        session.setAttribute(PARAMETER_ATTRIBUTE_TOUR_AMOUNT, tourAmount);
        session.setAttribute(SESSION_ATTRIBUTE_CURRENT_PAGE, page);

        Optional<Card> card = Optional.empty();
        if (user.getCardId() != 0) {
            try {
                card = cardService.findById(user.getCardId());
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }

        card.ifPresent(value -> session.setAttribute(SESSION_ATTRIBUTE_CARD_NUMBER,
                value.getCardNumber()));

        return new Router(page, FORWARD);
    }
}
