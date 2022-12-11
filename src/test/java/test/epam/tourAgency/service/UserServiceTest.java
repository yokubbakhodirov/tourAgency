package test.epam.tourAgency.service;

import com.company.tourAgency.entity.User;
import com.company.tourAgency.entity.enums.UserRole;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.UserService;
import com.company.tourAgency.service.impl.UserServiceImpl;
import com.company.tourAgency.utils.encoder.PasswordEncoder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User service test. Make sure you run tests together as they are connected to each other.
 * Provided email must be unique.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final PasswordEncoder encoder = new PasswordEncoder();
    private static final String email = "testGmail001@gmail.com";
    private static final User user = User.builder()
            .email(email)
            .password("1234")
            .name("alice")
            .lastname("johnson")
            .phone("+998711234567")
            .build();

    @Test
    @Order(1)
    void testSignUp() {
        try {
            boolean result = userService.signUp(user.getEmail(), user.getPassword(),
                    user.getName(), user.getLastname(), user.getPhone());

            assertTrue(result);
        } catch (ServiceException e) {
            fail("Sign up user method must not throw an exception");
        }
    }


    @Test
    @Order(2)
    void testFindByEmail() {
        try {
            Optional<User> userByEmail = userService.findByEmail(user.getEmail());
            userByEmail.ifPresent(value -> user.setId(value.getId()));

            assertTrue(userByEmail.isPresent());
            assertEquals(userByEmail.get().getEmail(), user.getEmail());
        } catch (ServiceException e) {
            fail("Find user by email method must not throw an exception");
        }
    }

    @Test
    @Order(3)
    void testFindById() {
        try {
            Optional<User> userById = userService.findById(user.getId());
            assertTrue(userById.isPresent());

            assertEquals(userById.get().getEmail(), user.getEmail());
        } catch (ServiceException e) {
            fail("Find user by id method must not throw an exception");
        }
    }

    @Test
    @Order(4)
    void testFindAll() {
        try {
            List<User> users = userService.findAll();

            assertFalse(users.isEmpty());
        } catch (ServiceException e) {
            fail("Find all users method must not throw an exception");
        }
    }

    @Test
    @Order(5)
    void testAuthenticate() {
        try {
            Optional<User> authenticatedUser =
                    userService.authenticate(user.getEmail(), user.getPassword());
            assertTrue(authenticatedUser.isPresent());

            assertEquals(authenticatedUser.get().getEmail(), user.getEmail());
        } catch (ServiceException e) {
            fail("Authenticate user method must not throw an exception");
        }
    }

    @Test
    @Order(6)
    void testUpdateUserRole() {
        try {
            boolean result = userService.updateUserRole(user.getId(), "admin");
            assertTrue(result);

            Optional<User> userById = userService.findById(user.getId());
            assertTrue(userById.isPresent());

            assertEquals(userById.get().getRole().name(), UserRole.ADMIN.name());
        } catch (ServiceException e) {
            fail("Update user role method must not throw an exception");
        }
    }

    @Test
    @Order(7)
    void testUpdatePassword() {
        try {
            boolean result = userService.updatePassword(user.getEmail(), "1122");
            assertTrue(result);

            Optional<User> userById = userService.findById(user.getId());
            assertTrue(userById.isPresent());

            String encodedPassword = encoder.encode("1122");
            assertEquals(userById.get().getPassword(), encodedPassword);
        } catch (ServiceException e) {
            fail("Update user password method must not throw an exception");
        } catch (NoSuchAlgorithmException e) {
            fail("Encode user password must not throw an exception while testing update user password");
        }
    }

    // as we are testing random email, we can't send message to it
//    @Test
//    @Order(8)
//    void testSendKeyToUpdatePassword() {
//        try {
//            Optional<String> key = userService.sendKeyToUpdatePassword(user.getEmail(), "en");
//
//            assertTrue(key.isPresent());
//        } catch (ServiceException e) {
//            fail("Send key to update password method must not throw an exception");
//        }
//    }

    @Test
    @Order(9)
    void testUpdateLoyal() {
        try {
            boolean result = userService.updateLoyal(true, user.getId());

            assertTrue(result);
        } catch (ServiceException e) {
            fail("Update isLoyal field of user method must not throw an exception");
        }
    }

    @Test
    @Order(10)
    void testDelete() {
        try {
            boolean actual = userService.delete(user.getId());

            assertTrue(actual);
        } catch (ServiceException e) {
            fail("Delete user method must not throw an exception");
        }
    }


}
