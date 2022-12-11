package com.company.tourAgency.service;

import com.company.tourAgency.entity.Tour;
import com.company.tourAgency.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service for manipulating tour data.
 */
public interface TourService {
    /**
     * Insert a new tour
     *
     * @param name tour name
     * @param type tour type: EXCURSION, REST, SHOPPING. case-insensitive
     * @param description tour description (max 1000 characters)
     * @param discount tour discount should be between 0 and 100
     * @param date tour date should be in the format (YYYY-MM-DDThh:mm), as date comes in this format from fronted
     * @param price price should be more than 0
     * @param imagePath tour image path (max 1000 characters)
     * @return true if it was inserted, false if tour with such name exists
     * @throws ServiceException if database access error occurs
     */
    boolean insert(String name, String type, String description,
                   Integer discount, String date, double price, String imagePath) throws ServiceException;


    /**
     * Find tour by id
     *
     * @param id tour id
     * @return tour inside Optional if exists, otherwise Optional.empty()
     * @throws ServiceException if database access error occurs
     */
    Optional<Tour> findById(int id) throws ServiceException;

    /**
     * Find tour by name
     *
     * @param name tour name
     * @return tour inside Optional if exists, otherwise Optional.empty()
     * @throws ServiceException if database access error occurs
     */
    Optional<Tour> findByName(String name) throws ServiceException;


    /**
     * Find tour by user id
     *
     * @param userId user id
     * @return map containing pairs: key is a tour, value is an amount of tour user bought
     * @throws ServiceException if database access error occurs
     */
    Map<Tour, Integer> findTourByUserId(int userId) throws ServiceException;


    /**
     * Find all tours
     *
     * @return list of tours
     * @throws ServiceException if database access error occurs
     */
    List<Tour> findAll() throws ServiceException;


    /**
     * Delete tour by id
     *
     * @param id tour id
     * @return true if it was deleted, otherwise false
     * @throws ServiceException if database access error occurs
     */
    boolean delete(int id) throws ServiceException;
}
