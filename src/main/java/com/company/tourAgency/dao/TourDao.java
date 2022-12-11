package com.company.tourAgency.dao;

import com.company.tourAgency.entity.Tour;
import com.company.tourAgency.exception.DaoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * DAO for tour data manipulation in table tours
 */
public interface TourDao extends BaseDao<Tour> {
    /**
     * Insert tour
     *
     * @param tour tour to insert
     * @return true if it was inserted, false if tour with such name already exists
     * @throws DaoException if database access error occurs
     */
    boolean insert(Tour tour) throws DaoException;

    /**
     * Find tour by id
     *
     * @param id tour id
     * @return tour inside Optional if tour with such id exists, otherwise Optional.empty()
     * @throws DaoException if database access error occurs
     */
    Optional<Tour> findById(int id) throws DaoException;

    /**
     * Find tour by name
     *
     * @param name tour name
     * @return tour inside Optional if tour with such name exists, otherwise Optional.empty()
     * @throws DaoException if database access error occurs
     */
    Optional<Tour> findByName(String name) throws DaoException;

    /**
     * Find tour by user id
     *
     * @param userId user id
     * @return map: key is tour, value is amount of tour user bought
     * @throws DaoException if database access error occurs
     */
    Map<Tour, Integer> findTourByUserId(int userId) throws DaoException;

    /**
     * Find all tours
     *
     * @return list of tours
     * @throws DaoException if database access error occurs
     */
    List<Tour> findAll() throws DaoException;

    /**
     * Unsupported update tour operation
     * @throws UnsupportedOperationException always
     */
    boolean update(Tour tour) throws DaoException;

    /**
     * Delete tour by id
     *
     * @param id tour id
     * @return true if it was deleted, otherwise false
     * @throws DaoException if database access error occurs
     */
    boolean delete(int id) throws DaoException;
}
