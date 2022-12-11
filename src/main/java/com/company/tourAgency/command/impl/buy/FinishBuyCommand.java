package com.company.tourAgency.command.impl.buy;

import com.company.tourAgency.command.Command;
import com.company.tourAgency.controller.navigation.Router;
import com.company.tourAgency.entity.Card;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.exception.CommandException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.impl.CardServiceImpl;
import com.company.tourAgency.service.impl.OrderServiceImpl;
import com.company.tourAgency.utils.validator.impl.CardFormValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.PageURL.BUY_PAGE;
import static com.company.tourAgency.controller.navigation.Router.Type.FORWARD;

public class FinishBuyCommand implements Command {
    private static final CardServiceImpl cardService = CardServiceImpl.getInstance();
    private static final OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private static final CardFormValidator validator = CardFormValidator.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);
        int userId = user.getId();
        String page = BUY_PAGE;

        Map<String, String> validationErrors =
                validator.validate(request.getParameter(PARAMETER_CARD_NUMBER),
                session.getAttribute(PARAMETER_ATTRIBUTE_TOUR_AMOUNT));

        if (validationErrors.isEmpty()) {
            String cardNumber = request.getParameter(PARAMETER_CARD_NUMBER);
            int tourAmount = Integer.parseInt((String) session.getAttribute(PARAMETER_ATTRIBUTE_TOUR_AMOUNT));
            int tourId = Integer.parseInt((String) session.getAttribute(PARAMETER_ATTRIBUTE_TOUR_ID));

            try {
                boolean result = false;
                boolean invalidCard = false;
                if (user.getCardId() == 0) {
                    result = cardService.insert(userId, cardNumber);
                    if (result) {
                        Optional<Card> card = cardService.findByCardNumber(cardNumber);
                        user.setCardId(card.get().getId());
                    }
                } else {
                    Optional<Card> card = cardService.findById(user.getCardId());
                    if (card.isPresent() && card.get().getCardNumber().equals(cardNumber)) {
                        result = true;
                    } else {
                        invalidCard = true;
                    }
                }

                session.setAttribute(SESSION_ATTRIBUTE_USER, user);
                if (invalidCard) {
                    request.setAttribute(REQUEST_ATTRIBUTE_FORM_INVALID, Map.of(PARAMETER_CARD_NUMBER, ""));
                } else if (!result) {
                    request.setAttribute(REQUEST_ATTRIBUTE_CARD_EXISTS, "");
                }
                if (result) {
                    result = orderService.insert(userId, tourId, tourAmount);
                }
                if (result) {
                    request.setAttribute(REQUEST_ATTRIBUTE_PURCHASE_SUCCESS, "");
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
