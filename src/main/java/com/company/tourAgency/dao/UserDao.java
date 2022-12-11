package com.company.tourAgency.dao;

import com.company.tourAgency.entity.User;
import com.company.tourAgency.entity.enums.UserRole;
import com.company.tourAgency.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * DAO for user data manipulation in table users
 */
public interface UserDao extends BaseDao<User> {
    /**
     * Insert user
     *
     * @param user with encrypted password
     * @return true if user was inserted, false if user with such email exists
     * @throws DaoException if database access error occurs
     */
    @Override
    boolean insert(User user) throws DaoException;

    /**
     * Find user by id
     *
     * @param id user id
     * @return user with encrypted password inside Optional if user with such id exists,
     * otherwise Optional.empty()
     * @throws DaoException if database access error occurs
     */
    @Override
    Optional<User> findById(int id) throws DaoException;

    /**
     * Find user by email
     *
     * @param email user email
     * @return user with encrypted password inside Optional if user with such email exists,
     * otherwise Optional.empty()
     * @throws DaoException if database access error occurs
     */
    Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Find all users
     *
     * @return list of users with encrypted passwords
     * @throws DaoException if database access error occurs
     */
    List<User> findAll() throws DaoException;

    /**
     * Authenticate user
     *
     * @param email user email
     * @param password user encrypted password
     * @return user inside Optional, if user with such email and password exists,
     * otherwise Optional.empty()
     * @throws DaoException if database access error occurs
     */
    Optional<User> authenticate(String email, String password) throws DaoException;

    /**
     * Unsupported update user operation
     * @throws UnsupportedOperationException always
     */
    @Override
    boolean update(User user) throws DaoException;

    /**
     * Update user role
     *
     * @param userId user id
     * @param role userRole: ADMIN or USER only
     * @return true if user exists and was updated, otherwise false
     * @throws DaoException if database access error occurs
     */
    boolean updateUserRole(int userId, UserRole role) throws DaoException;


    /**
     * Update user password
     *
     * @param userId user id
     * @param newPassword new encrypted password
     * @return true if user exists and was updated, otherwise false
     * @throws DaoException if database access error occurs
     */
    boolean updatePassword(int userId, String newPassword) throws DaoException;

    /**
     * Update isLoyal field of user
     *
     * @param isLoyal new isLoyal boolean value
     * @param userId user id
     * @return true if user exists and was updated, otherwise false
     * @throws DaoException if database access error occurs
     */
    boolean updateLoyal(boolean isLoyal, int userId) throws DaoException;

    /**
     * Add card to user
     *
     * @param id user id
     * @param cardId card id
     * @return true if user and card exist and user was updated
     * @throws DaoException if database access error occurs
     */
    boolean addCard(int id, int cardId) throws DaoException;

    /**
     * Delete user by id
     *
     * @param id user id
     * @return true if user was deleted, otherwise false
     * @throws DaoException if database access error occurs
     */
    @Override
    boolean delete(int id) throws DaoException;
}
