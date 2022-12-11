package com.company.tourAgency.dao.impl.tour;

import com.company.tourAgency.dao.TourDao;
import com.company.tourAgency.entity.Tour;
import com.company.tourAgency.entity.enums.TourType;
import com.company.tourAgency.exception.DaoException;
import com.company.tourAgency.utils.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.company.tourAgency.dao.impl.order.OrderSqlQuery.ORDER_FIND_BY_TOUR_ID;
import static com.company.tourAgency.dao.impl.order.OrderSqlQuery.ORDER_UPDATE_TOUR_ID_TO_NULL;
import static com.company.tourAgency.dao.impl.tour.TourSqlQuery.*;


public class TourDaoImpl implements TourDao {
    private static final Logger logger = LogManager.getLogger();

    private static TourDaoImpl instance;

    private TourDaoImpl() {
    }

    public static TourDaoImpl getInstance() {
        if (instance == null) {
            instance = new TourDaoImpl();
        }
        return instance;
    }


    @Override
    public boolean insert(Tour tour) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(TOUR_INSERT)) {
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getType().name());
            statement.setString(3, tour.getDescription());
            statement.setInt(4, tour.getDiscount());
            statement.setTimestamp(5, tour.getDate());
            statement.setDouble(6, tour.getPrice());
            statement.setString(7, tour.getImagePath());

            boolean result = statement.executeUpdate() == 1;
            ConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to insert a new tour: " + e.getMessage());
            throw new DaoException("Failed to insert a new tour", e);
        }
    }

    @Override
    public Optional<Tour> findById(int id) throws DaoException {
        Optional<Tour> tour = Optional.empty();

        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(TOUR_FIND_BY_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                tour = Optional.of(mapTour(rs));
            }
        } catch (SQLException e) {
            logger.error("Failed to find tour with id = {} : {}", id, e.getMessage());
            throw new DaoException("Failed to find tour with id = " + id, e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return tour;
    }

    @Override
    public Optional<Tour> findByName(String name) throws DaoException {
        Optional<Tour> tour = Optional.empty();

        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(TOUR_FIND_BY_NAME)) {

            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                tour = Optional.of(mapTour(rs));
            }
        } catch (SQLException e) {
            logger.error("Failed to find tour with name = {} : {}", name, e.getMessage());
            throw new DaoException("Failed to find tour with name = " + name, e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return tour;
    }

    @Override
    public Map<Tour, Integer> findTourByUserId(int userId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(TOUR_FIND_BY_USER_ID)) {

            Map<Tour, Integer> toursWithAmount = new LinkedHashMap<>();
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map.Entry<Tour, Integer> entry = mapTourAndAmount(rs);
                System.out.println(entry);
                toursWithAmount.put(entry.getKey(), entry.getValue());
            }

            ConnectionPool.getInstance().releaseConnection(connection);
            return toursWithAmount;
        } catch (SQLException e) {
            logger.error("Failed to find tours by userId = {} : {}", userId, e.getMessage());
            throw new DaoException("Failed to find tours by userId", e);
        }
    }

    @Override
    public List<Tour> findAll() throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(TOUR_FIND_ALL)) {

            List<Tour> tours = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tour tour = mapTour(rs);
                tours.add(tour);
            }

            ConnectionPool.getInstance().releaseConnection(connection);
            return tours;
        } catch (SQLException e) {
            logger.error("Failed to find all tours : {}", e.getMessage());
            throw new DaoException("Failed to find all tours", e);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        Optional<Tour> tour = findById(id);
        if (tour.isEmpty()) {
            return false;
        }

        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = true;
        boolean hasOrder = false;
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(ORDER_FIND_BY_TOUR_ID)) {

                statement.setInt(1, tour.get().getId());
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    hasOrder = true;
                }
            }

            if (hasOrder) {
                try (PreparedStatement statement = connection.prepareStatement(ORDER_UPDATE_TOUR_ID_TO_NULL)) {

                    statement.setInt(1, id);
                    result = statement.executeUpdate() >= 1;
                }
            }

            if (result) {
                try (PreparedStatement statement = connection.prepareStatement(TOUR_DELETE)) {

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
            logger.error("Failed to delete tour with id = {} : {}", id, e.getMessage());
            throw new DaoException("Failed to delete tour with id = " + id, e);
        }
    }

    @Override
    public boolean update(Tour tour) throws DaoException {
        throw new UnsupportedOperationException();
    }

    private Tour mapTour(ResultSet rs) throws SQLException {
        return Tour.builder()
                .id(rs.getInt(TOUR_ID_COLUMN))
                .name(rs.getString(TOUR_NAME_COLUMN))
                .type(TourType.valueOf(rs.getString(TOUR_TYPE_COLUMN)))
                .description(rs.getString(TOUR_DESCRIPTION_COLUMN))
                .discount(rs.getInt(TOUR_DISCOUNT_COLUMN))
                .date(rs.getTimestamp(TOUR_DATE_COLUMN))
                .price(rs.getDouble(TOUR_PRICE_COLUMN))
                .imagePath(rs.getString(TOUR_IMAGE_PATH_COLUMN))
                .build();
    }

    private Map.Entry<Tour, Integer> mapTourAndAmount(ResultSet rs) throws SQLException {
        Tour tour = Tour.builder()
                .id(rs.getInt(TOUR_ID_COLUMN))
                .name(rs.getString(TOUR_NAME_COLUMN))
                .type(TourType.valueOf(rs.getString(TOUR_TYPE_COLUMN)))
                .description(rs.getString(TOUR_DESCRIPTION_COLUMN))
                .discount(rs.getInt(TOUR_DISCOUNT_COLUMN))
                .date(rs.getTimestamp(TOUR_DATE_COLUMN))
                .price(rs.getDouble(TOUR_PRICE_COLUMN))
                .imagePath(rs.getString(TOUR_IMAGE_PATH_COLUMN))
                .build();
        return new AbstractMap.SimpleEntry<>(tour, rs.getInt(ORDER_AMOUNT_COLUMN));
    }
}
