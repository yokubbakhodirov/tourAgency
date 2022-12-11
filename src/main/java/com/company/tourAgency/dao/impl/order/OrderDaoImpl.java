package com.company.tourAgency.dao.impl.order;

import com.company.tourAgency.dao.OrderDao;
import com.company.tourAgency.entity.Order;
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

import static com.company.tourAgency.dao.impl.order.OrderSqlQuery.*;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger();

    private static OrderDaoImpl instance;

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(Order order) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ORDER_INSERT)) {

            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getTourId());
            statement.setInt(3, order.getAmount());

            boolean result = statement.executeUpdate() == 1;
            System.out.println(result);
            ConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to insert a new order: " + e.getMessage());
            throw new DaoException("Failed to insert a new order", e);
        }
    }

    @Override
    public Optional<Order> findById(int id) throws DaoException {
        Optional<Order> order = Optional.empty();

        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ORDER_FIND_BY_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                order = Optional.of(mapOrder(rs));
            }
        } catch (SQLException e) {
            logger.error("Failed to find order with id = {} : {}", id, e.getMessage());
            throw new DaoException("Failed to find order with id = " + id, e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return order;
    }

    @Override
    public Optional<Order> findLastAdded() throws DaoException {
        Optional<Order> order = Optional.empty();

        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ORDER_FIND_LAST_ADDED)) {

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                order = Optional.of(mapOrder(rs));
            }
        } catch (SQLException e) {
            logger.error("Failed to find last added order : {}", e.getMessage());
            throw new DaoException("Failed to find last added order", e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return order;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ORDER_FIND_ALL)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = mapOrder(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error("Failed to find all orders: {}", e.getMessage());
            throw new DaoException("Failed to find all orders", e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return orders;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ORDER_DELETE)) {

            statement.setInt(1, id);
            boolean result = statement.executeUpdate() == 1;
            ConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (SQLException e) {
            logger.error("Failed to delete order with id = {} : {}", id, e.getMessage());
            throw new DaoException("Failed to delete order with id = " + id, e);
        }
    }

    @Override
    public boolean update(Order t) throws DaoException {
        throw new UnsupportedOperationException();
    }

    private Order mapOrder(ResultSet rs) throws SQLException {
        return Order.builder()
                .id(rs.getInt(ORDER_ID_COLUMN))
                .userId(rs.getInt(ORDER_USER_ID_COLUMN))
                .tourId(rs.getInt(ORDER_TOUR_ID_COLUMN))
                .amount(rs.getInt(ORDER_AMOUNT_COLUMN))
                .orderDate((rs.getTimestamp(ORDER_DATE_COLUMN)))
                .isDeleted(rs.getBoolean(ORDER_IS_DELETED_COLUMN))
                .build();
    }
}
