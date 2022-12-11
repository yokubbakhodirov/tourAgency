package com.company.tourAgency.dao;

import com.company.tourAgency.entity.Card;
import com.company.tourAgency.exception.DaoException;

import java.util.Optional;

/**
 * DAO for card data manipulation in table card
 */
public interface CardDao extends BaseDao<Card> {

    /**
     * Insert a new card, also update user cardId
     *
     * @param userId user id
     * @param card card to insert
     * @return true if it was inserted, false if card with such card number already exists
     * @throws DaoException if database access error occurs
     */
    boolean insert(int userId, Card card) throws DaoException;

    /**
     * Find card by id
     *
     * @param id card id
     * @return card inside Optional if card with such id exists, otherwise Optional.empty()
     * @throws DaoException if database access error occurs
     */
    Optional<Card> findById(int id) throws DaoException;

    /**
     * Find card by card number
     *
     * @param cardNumber card number
     * @return card inside Optional if card with such card number exists,
     * otherwise Optional.empty()
     * @throws DaoException if database access error occurs
     */
    Optional<Card> findByCardNumber(String cardNumber) throws DaoException;

    /**
     * Unsupported update card operation
     * @throws UnsupportedOperationException always
     */
    boolean update(Card card) throws DaoException;

    /**
     * Delete card by id
     *
     * @param id card id
     * @return true if it was deleted, otherwise false
     * @throws DaoException if database access error occurs
     */
    boolean delete(int id) throws DaoException;
}
