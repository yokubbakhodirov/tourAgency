package com.company.tourAgency.service.impl;

import com.company.tourAgency.dao.impl.user.UserDaoImpl;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.entity.enums.UserRole;
import com.company.tourAgency.exception.DaoException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.UserService;
import com.company.tourAgency.utils.encoder.PasswordEncoder;
import com.company.tourAgency.utils.locale.ResourceBundleManager;
import com.company.tourAgency.utils.mail.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import static com.company.tourAgency.utils.locale.LocalizedKey.MAIL_CONFIRM_KEY_HEADER;
import static com.company.tourAgency.utils.locale.LocalizedKey.MAIL_CONFIRM_KEY_TEXT;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static UserServiceImpl instance;
    private static UserDaoImpl userDao;
    private static PasswordEncoder encoder;
    private final ResourceBundleManager resourceBundleManager;

    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
        encoder = new PasswordEncoder();
        resourceBundleManager = ResourceBundleManager.getInstance();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean signUp(String email, String password, String name,
                          String lastname, String phone) throws ServiceException {

        try {
            String encodedPassword = encoder.encode(password);

            User user = User.builder()
                    .email(email)
                    .password(encodedPassword)
                    .name(name)
                    .lastname(lastname)
                    .phone(phone)
                    .build();

            return userDao.insert(user);
        } catch (NoSuchAlgorithmException | DaoException e) {
            throw new ServiceException(e);
        }


    }

    @Override
    public Optional<User> findById(int id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        try {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> authenticate(String email, String password) throws ServiceException {
        try {
            Optional<User> user;
            String encodedPassword = encoder.encode(password);
            user = userDao.authenticate(email, encodedPassword);
            return user;
        } catch (DaoException | NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUserRole(int userId, String role) throws ServiceException {
        try {
            Optional<User> user = findById(userId);
            if (user.isEmpty()) {
                logger.error("User with userId = {} doesn't exist", userId);
                return false;
            }
            UserRole userRole;
            if (role != null) {
                try {
                    userRole = UserRole.valueOf(role.toUpperCase());
                } catch (IllegalArgumentException e) {
                    logger.error("Invalid user role passed: {}", e.getMessage());
                    return false;
                }
            } else {
                logger.error("User role is null");
                return false;
            }

            return userDao.updateUserRole(userId, userRole);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updatePassword(String email, String newPassword) throws ServiceException {
        try {
            Optional<User> user = findByEmail(email);
            if (user.isPresent()) {
                String encodedPassword = encoder.encode(newPassword);
                return userDao.updatePassword(user.get().getId(), encodedPassword);
            } else {
                logger.error("User with email = {} doesn't exist", email);
                return false;
            }
        } catch (DaoException | NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateLoyal(boolean isLoyal, int userId) throws ServiceException {
        try {
            return userDao.updateLoyal(isLoyal, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<String> sendKeyToUpdatePassword(String email, String locale) throws ServiceException {
        if (email == null) {
            logger.error("Passed null as email");
            return Optional.empty();
        }

        Optional<User> user = null;
        try {
            user = userDao.findByEmail(email);
            if (user.isEmpty()) {
                logger.error("Failed to find user with email {}", email);
                return Optional.empty();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        MailSender sender = MailSender.getInstance();
        String key = String.valueOf(new Random().nextInt(10000, 100000));
        ResourceBundle resourceBundle = resourceBundleManager.getResourceBundle(locale);

        if (sender.sendMail(email,
                resourceBundle.getString(MAIL_CONFIRM_KEY_HEADER),
                String.format(resourceBundle.getString(MAIL_CONFIRM_KEY_TEXT), key))) {
            return Optional.of(key);
        } else {
            throw new ServiceException("Failed to send key to email: " + user.get().getEmail());
        }
    }

    @Override
    public boolean addCard(int id, int cardId) throws ServiceException {
        try {
            return userDao.addCard(id, cardId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
