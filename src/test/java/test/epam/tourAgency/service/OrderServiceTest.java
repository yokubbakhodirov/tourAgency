package test.epam.tourAgency.service;

import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.OrderService;
import com.company.tourAgency.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Order service test. Make sure you run tests together as they are connected to each other.
 *  Provide ids of existing user and tour.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderServiceTest {
    private static final OrderService orderService = OrderServiceImpl.getInstance();

    private static final int userId = 0;
    private static final int tourId = 0;
    private static final com.company.tourAgency.entity.Order order =
            com.company.tourAgency.entity.Order.builder()
            .userId(userId)
            .tourId(tourId)
            .amount(1)
            .build();

    @Test
    @Order(1)
    void testInsert() {
        try {
            boolean result = orderService.insert(userId, tourId, 1);

            assertTrue(result);
        } catch (ServiceException e) {
            fail("Insert order method must not throw an exception");
        }
    }

    @Test
    @Order(2)
    void testFindLastAdded() {
        try {
            Optional<com.company.tourAgency.entity.Order> lastAdded = orderService.findLastAdded();
            lastAdded.ifPresent(value -> order.setId(value.getId()));

            assertTrue(lastAdded.isPresent());
            assertEquals(lastAdded.get().getUserId(), order.getUserId());
            assertEquals(lastAdded.get().getTourId(), order.getTourId());
        } catch (ServiceException e) {
            fail("Find last added order method must not throw an exception");
        }
    }

    @Test
    @Order(3)
    void testFindById() {
        try {
            Optional<com.company.tourAgency.entity.Order> orderById = orderService.findById(order.getId());
            orderById.ifPresent(value -> order.setId(value.getId()));

            assertTrue(orderById.isPresent());
            assertEquals(orderById.get().getUserId(), order.getUserId());
            assertEquals(orderById.get().getTourId(), order.getTourId());
        } catch (ServiceException e) {
            fail("Find last added order method must not throw an exception");
        }
    }

    @Test
    @Order(4)
    void testFindAll() {
        try {
            List<com.company.tourAgency.entity.Order> orders = orderService.findAll();

            assertFalse(orders.isEmpty());
        } catch (ServiceException e) {
            fail("Find all orders method must not throw an exception");
        }
    }

    @Test
    @Order(5)
    void testDelete() {
        try {
            boolean result = orderService.delete(order.getId());

            assertTrue(result);
        } catch (ServiceException e) {
            fail("Delete order method must not throw an exception");
        }
    }
}
