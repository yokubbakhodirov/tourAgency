package com.company.tourAgency.service;

import com.company.tourAgency.entity.Order;
import com.company.tourAgency.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service for manipulating order data.
 */
public interface OrderService {
    /**
     * Insert a new order
     *
     * @param userId id of user who is making an order
     * @param tourId id of tour which is being bought
     * @param tourAmount amount of tour
     * @return true if it was inserted, otherwise false
     * @throws ServiceException if database access error occurs
     */
    boolean insert(int userId, int tourId, int tourAmount) throws ServiceException;


    /**
     * Find order by id
     *
     * @param id order id
     * @return order inside Optional if exists, otherwise Optional.empty()
     * @throws ServiceException if database access error occurs
     */
    Optional<Order> findById(int id) throws ServiceException;

    /**
     * Find last added order
     *
     * @return order inside Optional if exists, otherwise false
     * @throws ServiceException if database access error occurs
     */
    Optional<Order> findLastAdded() throws ServiceException;


    /**
     * Find all orders
     *
     * @return list of orders
     * @throws ServiceException if database access error occurs
     */
    List<Order> findAll() throws ServiceException;


    /**
     * Unsupported update order operation
     * @throws UnsupportedOperationException always
     */
    boolean update(Order t) throws ServiceException;


    /**
     * Delete order by id
     *
     * @param id order id
     * @return true if it was deleted, otherwise false
     * @throws ServiceException if database access error occurs
     */
    boolean delete(int id) throws ServiceException;
}
