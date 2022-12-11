package com.company.tourAgency.dao.impl.card;

import com.company.tourAgency.dao.CardDao;
import com.company.tourAgency.entity.Card;
import com.company.tourAgency.exception.DaoException;
import com.company.tourAgency.utils.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.company.tourAgency.dao.impl.card.CardSqlQuery.*;
import static com.company.tourAgency.dao.impl.user.UserSqlQuery.*;

public class CardDaoImpl implements CardDao {
    private static final Logger logger = LogManager.getLogger();

    private static CardDaoImpl instance;

    private CardDaoImpl() {
    }

    public static CardDaoImpl getInstance() {
        if (instance == null) {
            instance = new CardDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(Card card) throws DaoException {
        return false;
    }

    @Override
    public boolean insert(int userId, Card card) throws DaoException {
        if (findByCardNumber(card.getCardNumber()).isPresent()) {
            return false;
        }

        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean result;
            try (PreparedStatement statement = connection.prepareStatement(CARD_INSERT)) {

                statement.setString(1, card.getCardNumber());

                result = statement.executeUpdate() == 1;
            }
            Optional<Card> resultCard = Optional.empty();
            if (result) {
                try (PreparedStatement statement =
                             connection.prepareStatement(CARD_FIND_BY_CARD_NUMBER)) {

                    statement.setString(1, card.getCardNumber());

                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        resultCard = Optional.of(mapCard(rs));
                    } else {
                        result = false;
                    }
                }
            }

            if (result) {
                Card insertedCard = resultCard.get();
                try (PreparedStatement statement = connection.prepareStatement(USER_UPDATE_ADD_CARD)) {

                    statement.setInt(1, insertedCard.getId());
                    statement.setInt(2, userId);

                    result = statement.executeUpdate() == 1;
                }
            }

            if (result) {
                connection.commit();
            } else {
                logger.error("Failed to insert a new card");
                connection.rollback();
            }

            ConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to insert a new card: " + e.getMessage());
            throw new DaoException("Failed to insert a new card", e);
        }
    }

    @Override
    public Optional<Card> findById(int id) throws DaoException {
        Optional<Card> card = Optional.empty();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CARD_FIND_BY_ID)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                card = Optional.of(mapCard(rs));
            }
        } catch (SQLException e) {
            logger.error("Failed to find card with id = {} : {}", id, e.getMessage());
            throw new DaoException("Failed to find card with id = " + id, e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return card;
    }

    @Override
    public Optional<Card> findByCardNumber(String cardNumber) throws DaoException {
        Optional<Card> card = Optional.empty();

        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CARD_FIND_BY_CARD_NUMBER)) {

            statement.setString(1, cardNumber);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                card = Optional.of(mapCard(rs));
            }
        } catch (SQLException e) {
            logger.error("Failed to find card with card number = {} : {}", cardNumber, e.getMessage());
            throw new DaoException("Failed to find card with card number = " + cardNumber, e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return card;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean result = true;
            boolean hasOwner = false;
            try (PreparedStatement statement = connection.prepareStatement(USER_FIND_BY_CARD_ID)) {
                statement.setInt(1, id);

                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    hasOwner = true;
                }
            }

            if (hasOwner) {
                try (PreparedStatement statement = connection.prepareStatement(USER_UPDATE_CARD_ID_TO_NULL)) {
                    statement.setInt(1, id);

                    result = statement.executeUpdate() == 1;
                }
            }

            if (result) {
                try (PreparedStatement statement = connection.prepareStatement(CARD_DELETE)) {
                    statement.setInt(1, id);

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
            logger.error("Failed to delete card with id = {} : {}", id, e.getMessage());
            throw new DaoException("Failed to delete card with id = " + id, e);
        }
    }

    @Override
    public boolean update(Card card) throws DaoException {
        throw new UnsupportedOperationException();
    }

    private Card mapCard(ResultSet rs) throws SQLException {
        return Card.builder()
                .id(rs.getInt(CARD_COLUMN_ID))
                .cardNumber(rs.getString(CARD_COLUMN_CARD_NUMBER))
                .build();
    }
}
