package com.company.tourAgency.service;

import com.company.tourAgency.entity.Card;
import com.company.tourAgency.exception.ServiceException;

import java.util.Optional;

/**
 * Service for manipulating card data.
 */

public interface CardService {
    /**
     * Insert a new card
     *
     * @param userId id of user who is an owner of this card
     * @param cardNumber card number
     * @return true if it was inserted, false if card with such card number already exists
     * @throws ServiceException if database access error occurs
     */
    boolean insert(int userId, String cardNumber) throws ServiceException;


    /**
     * Find card by id
     *
     * @param id card id
     * @return card inside Optional if exists, otherwise Optional.empty()
     * @throws ServiceException if database access error occurs
     */
    Optional<Card> findById(int id) throws ServiceException;


    /**
     * Find card by card number
     *
     * @param cardNumber card number
     * @return  card inside Optional if exists, otherwise Optional.empty()
     * @throws ServiceException if database access error occurs
     */
    Optional<Card> findByCardNumber(String cardNumber) throws ServiceException;


    /**
     * Unsupported update card operation
     * @throws UnsupportedOperationException always
     */
    boolean update(Card card) throws ServiceException;


    /**
     * Delete card by id
     *
     * @param id card id
     * @return true if it was deleted, otherwise false
     * @throws ServiceException if database access error occurs
     */
    boolean delete(int id) throws ServiceException;
}
