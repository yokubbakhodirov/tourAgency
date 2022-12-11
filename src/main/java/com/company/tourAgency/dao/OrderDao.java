package com.company.tourAgency.dao;

import com.company.tourAgency.entity.Order;
import com.company.tourAgency.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * DAO for order data manipulation in table orders
 */
public interface OrderDao extends BaseDao<Order> {
    /**
     * Insert a new order
     *
     * @param order order
     * @return true if it was inserted, otherwise false
     * @throws DaoException if database access error occurs
     */
    boolean insert(Order order) throws DaoException;

    /**
     * Find order by id
     *
     * @param id order id
     * @return order inside Optional if order with such id exists, otherwise Optional.empty()
     * @throws DaoException if database access error occurs
     */
    Optional<Order> findById(int id) throws DaoException;

    /**
     * Find last added order
     *
     * @return order inside Optional if exists, otherwise false
     * @throws DaoException if database access error occurs
     */
    Optional<Order> findLastAdded() throws DaoException;

    /**
     * Find all orders
     *
     * @return list of orders
     * @throws DaoException if database access error occurs
     */
    List<Order> findAll() throws DaoException;

    /**
     * Unsupported update order operation
     * @throws UnsupportedOperationException always
     */
    boolean update(Order order) throws DaoException;

    /**
     * Delete order by id
     *
     * @param id order id
     * @return true if it was deleted, otherwise false
     * @throws DaoException if database access error occurs
     */
    boolean delete(int id) throws DaoException;
}
