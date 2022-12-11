package com.company.tourAgency.dao.impl.user;

import com.company.tourAgency.dao.UserDao;
import com.company.tourAgency.entity.User;
import com.company.tourAgency.entity.enums.UserRole;
import com.company.tourAgency.exception.DaoException;
import com.company.tourAgency.utils.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.company.tourAgency.dao.impl.order.OrderSqlQuery.ORDER_FIND_BY_USER_ID;
import static com.company.tourAgency.dao.impl.order.OrderSqlQuery.ORDER_UPDATE_USER_ID_TO_NULL;
import static com.company.tourAgency.dao.impl.user.UserSqlQuery.*;
import static com.company.tourAgency.dao.impl.card.CardSqlQuery.CARD_DELETE;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static UserDaoImpl instance;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        Optional<User> userByEmail = findByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            return false;
        }

        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(USER_INSERT)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getLastname());
            statement.setString(5, user.getPhone());

            boolean result = statement.executeUpdate() == 1;
            ConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to insert a new user: " + e.getMessage());
            throw new DaoException("Failed to insert a new user", e);
        }
    }

    @Override
    public Optional<User> findById(int id) throws DaoException {
        Optional<User> user = Optional.empty();

        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(USER_FIND_BY_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user = Optional.of(mapUser(rs));
            }
        } catch (SQLException e) {
            logger.error("Failed to find user with id = {} : {}", id, e.getMessage());
            throw new DaoException("Failed to find user with id = " + id, e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> user = Optional.empty();
        Connection connection = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(USER_FIND_BY_EMAIL)) {

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user = Optional.of(mapUser(rs));
            }
        } catch (SQLException e) {
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return user;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(USER_FIND_ALL)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = mapUser(rs);
                users.add(user);
            }

        } catch (SQLException e) {
            logger.error("Failed to find users : {}", e.getMessage());
            throw new DaoException("Failed to find users", e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return users;
    }

    @Override
    public Optional<User> authenticate(String email, String password) throws DaoException {
        Optional<User> user = Optional.empty();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(USER_AUTHENTICATE)) {

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = Optional.of(mapUser(rs));
            }
        } catch (SQLException e) {
            logger.error("Failed to authenticate; {}", e.getMessage());
            throw new DaoException("Failed to authenticate ", e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return user;
    }

    @Override
    public boolean updateUserRole(int userId, UserRole role) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE)) {

            statement.setString(1, role.name());
            statement.setInt(2, userId);

            boolean result = statement.executeUpdate() == 1;
            ConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to update role of user with id = {} : {}", userId, e.getMessage());
            throw new DaoException("Failed to update role of user with id = " + userId, e);
        }
    }

    @Override
    public boolean updatePassword(int userId, String password) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(USER_UPDATE_PASSWORD)) {

            statement.setString(1, password);
            statement.setInt(2, userId);

            boolean result = statement.executeUpdate() == 1;
            ConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to update password of user with id = {} : {}", userId, e.getMessage());
            throw new DaoException("Failed to update password of user with id = " + userId, e);
        }
    }

    @Override
    public boolean updateLoyal(boolean isLoyal, int userId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(USER_UPDATE_LOYAL)) {

            statement.setBoolean(1, isLoyal);
            statement.setInt(2, userId);

            boolean result = statement.executeUpdate() == 1;
            ConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addCard(int id, int cardId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(USER_UPDATE_ADD_CARD)) {

            statement.setInt(1, cardId);
            statement.setInt(2, id);

            boolean result = statement.executeUpdate() == 1;
            ConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to add card of user with id = {} : {}", id, e.getMessage());
            throw new DaoException("Failed to add card of user with id = " + id, e);
        }

    }

    @Override
    public boolean delete(int id) throws DaoException {
        Optional<User> user = findById(id);
        if (user.isEmpty()) {
            return false;
        }

        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = true;
        boolean hasOrder = false;
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(ORDER_FIND_BY_USER_ID)) {

                statement.setInt(1, user.get().getId());
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    hasOrder = true;
                }
            }

            if (hasOrder) {
                try (PreparedStatement statement = connection.prepareStatement(ORDER_UPDATE_USER_ID_TO_NULL)) {

                    statement.setInt(1, id);
                    result = statement.executeUpdate() >= 1;
                }
            }

            if (result) {
                try (PreparedStatement statement = connection.prepareStatement(USER_DELETE)) {

                    statement.setInt(1, id);
                    result = statement.executeUpdate() == 1;
                }
            }

            if (result && user.get().getCardId() != 0) {
                try (PreparedStatement statement = connection.prepareStatement(CARD_DELETE)) {

                    statement.setInt(1, user.get().getCardId());
                    result = statement.executeUpdate() == 1;
                }
            }

            if (result) {
                connection.commit();
            } else {
                connection.rollback();
            }

            ConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to delete user with id = {} : {}", id, e.getMessage());
            throw new DaoException("Failed to delete user with id = " + id, e);
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        throw new UnsupportedOperationException("Update operation of user is not supported");
    }

    private User mapUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getInt(USER_ID_COLUMN))
                .email(rs.getString(USER_EMAIL_COLUMN))
                .password(rs.getString(USER_PASSWORD_COLUMN))
                .name(rs.getString(USER_NAME_COLUMN))
                .lastname(rs.getString(USER_LASTNAME_COLUMN))
                .phone(rs.getString(USER_PHONE_COLUMN))
                .role(UserRole.valueOf(rs.getString(USER_ROLE_COLUMN)))
                .cardId(rs.getInt(USER_CARD_ID_COLUMN))
                .isLoyal(rs.getBoolean(USER_IS_LOYAL_COLUMN))
                .createdAt(rs.getDate(USER_CREATED_AT_COLUMN))
                .isDeleted(rs.getBoolean(USER_IS_DELETED_COLUMN))
                .build();
    }
}
