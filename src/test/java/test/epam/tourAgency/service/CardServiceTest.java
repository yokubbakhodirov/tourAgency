package test.epam.tourAgency.service;

import com.company.tourAgency.entity.Card;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.CardService;
import com.company.tourAgency.service.impl.CardServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Card service test. Make sure you run tests together as they are connected to each other
 *  and also make sure you provide unique card number and id of existing user.
 * After running all tests, cardId of user you provided will be set to null
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardServiceTest {
    private static final CardService cardService = CardServiceImpl.getInstance();
    private static final int userId = 0;
    private static final String cardNumber = "000011110000";

    private static final Card card = Card.builder()
            .cardNumber(cardNumber)
            .build();

    @Test
    @Order(1)
    void testInsert() {
        try {
            boolean result = cardService.insert(userId, cardNumber);

            assertTrue(result);
        } catch (ServiceException e) {
            fail("Insert card method must not throw an exception");
        }
    }

    @Test
    @Order(2)
    void testFindByCardNumber() {
        try {
            Optional<Card> cardByNumber = cardService.findByCardNumber(cardNumber);
            cardByNumber.ifPresent(value -> card.setId(value.getId()));

            assertTrue(cardByNumber.isPresent());
            assertEquals(cardByNumber.get().getCardNumber(), cardNumber);
        } catch (ServiceException e) {
            fail("Find card by number method must not throw an exception");
        }
    }

    @Test
    @Order(3)
    void testFindById() {
        try {
            Optional<Card> cardById = cardService.findById(card.getId());

            assertTrue(cardById.isPresent());
            assertEquals(cardById.get().getCardNumber(), cardNumber);
        } catch (ServiceException e) {
            fail("Find card by id method must not throw an exception");
        }
    }

    @Test
    @Order(4)
    void delete() {
        try {
            boolean result = cardService.delete(card.getId());

            assertTrue(result);
        } catch (ServiceException e) {
            fail("Delete card method must not throw an exception");
        }
    }

}
