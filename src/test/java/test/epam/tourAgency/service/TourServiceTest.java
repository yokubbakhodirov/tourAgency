package test.epam.tourAgency.service;

import com.company.tourAgency.entity.Tour;
import com.company.tourAgency.entity.enums.TourType;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.TourService;
import com.company.tourAgency.service.impl.TourServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tour service test. Make sure you run tests together as they are connected to each other.
 * Provide id of existing user, if he bought a tour, set userBoughtTour to true, otherwise to false.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TourServiceTest {
    private static final TourService tourService = TourServiceImpl.getInstance();

    private static final int userId = 0;
    private static final boolean userBoughtTour = false;
    private static final Tour tour = Tour.builder()
            .name("Navoi")
            .type(TourType.EXCURSION)
            .description("one of the oldest cities of Uzbekistan")
            .discount(0)
            .date(Timestamp.valueOf(LocalDateTime.now()))
            .price(500)
            .imagePath("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DLr25_7laie8&psig=AOvVaw3KbSBQBh55JX6nHABCmpjA&ust=1670738268645000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCOj5oZ2v7vsCFQAAAAAdAAAAABAI")
            .build();

    @Test
    @Order(1)
    void testInsert() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
            String formattedDate = tour.getDate().toLocalDateTime().format(formatter);
            formattedDate = formattedDate.replace(" ", "T");

            boolean result = tourService.insert(tour.getName(), tour.getType().name(),
                    tour.getDescription(), tour.getDiscount(), formattedDate,
                    tour.getPrice(), tour.getImagePath());

            assertTrue(result);
        } catch (ServiceException e) {
            fail("Insert tour method must not throw an exception");
        }
    }

    @Test
    @Order(2)
    void testFindByName() {
        try {
            Optional<Tour> tourByName = tourService.findByName(tour.getName());
            tourByName.ifPresent(value -> tour.setId(value.getId()));

            assertTrue(tourByName.isPresent());
        } catch (ServiceException e) {
            fail("Find tour by name method must not throw an exception");
        }
    }

    @Test
    @Order(3)
    void testFindById() {
        try {
            Optional<Tour> tourById = tourService.findById(tour.getId());

            assertTrue(tourById.isPresent());
        } catch (ServiceException e) {
            fail("Find tour by id method must not throw an exception");
        }
    }

    @Test
    @Order(4)
    void testFindTourByUserId() {
        try {
            Map<Tour, Integer> toursAndAmount = tourService.findTourByUserId(userId);

            if (userBoughtTour) {
                assertFalse(toursAndAmount.isEmpty());
            } else {
                assertTrue(toursAndAmount.isEmpty());
            }
        } catch (ServiceException e) {
            fail("Find tour by user id method must not throw an exception");
        }
    }

    @Test
    @Order(5)
    void findAll() {
        try {
            List<Tour> tours = tourService.findAll();

            assertFalse(tours.isEmpty());
        } catch (ServiceException e) {
            fail("Find all tours method must not throw an exception");
        }
    }

    @Test
    @Order(6)
    void delete() {
        try {
            boolean result = tourService.delete(tour.getId());

            assertTrue(result);
        } catch (ServiceException e) {
            fail("Delete tour method must not throw an exception");
        }
    }
}
