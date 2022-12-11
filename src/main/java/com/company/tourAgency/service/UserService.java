package com.company.tourAgency.service;

import com.company.tourAgency.entity.User;
import com.company.tourAgency.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service for manipulating user data.
 */
public interface UserService {

    /**
     * Insert a new user with default role of USER
     *
     * @param email user email
     * @param password user non-encrypted password
     * @param name name of user
     * @param lastname user lastname
     * @param phone user phone
         * @return true if it was inserted, false if user with such email exists
     * @throws ServiceException if database access error occurs
     */
    boolean signUp(String email, String password, String name,
                   String lastname, String phone) throws ServiceException;


    /**
     * Find user by id
     *
     * @param id user id
     * @return user with encrypted password inside Optional if exists, otherwise Optional.empty()
     * @throws ServiceException if database access error occurs
     */
    Optional<User> findById(int id) throws ServiceException;


    /**
     * Find user by email
     *
     * @param email user email
     * @return user with encrypted password inside Optional if exists, otherwise Optional.empty()
     * @throws ServiceException if database access error occurs
     */
    Optional<User> findByEmail(String email) throws ServiceException;


    /**
     * Find all users
     *
     * @return list of users with encrypted passwords
     * @throws ServiceException if database access error occurs
     */
    List<User> findAll() throws ServiceException;


    /**
     * Authenticate user
     *
     * @param email user email
     * @param password user non-encrypted password
     * @return user with encrypted password inside Optional if exists, otherwise Optional.empty()
     * @throws ServiceException if database access error occurs
     */
    Optional<User> authenticate(String email, String password) throws ServiceException;


    /**
     * Update user role
     *
     * @param userId user id
     * @param role userRole: ADMIN or USER only (case-insensitive)
     * @return true if user exists and was updated, otherwise false
     * @throws ServiceException if database access error occurs
     */
    boolean updateUserRole(int userId, String role) throws ServiceException;


    /**
     * Update password
     *
     * @param email user email
     * @param newPassword new non-encrypted password
     * @return true if user exists and was updated, otherwise false
     * @throws ServiceException if database access error occurs
     */
    boolean updatePassword(String email, String newPassword) throws ServiceException;


    /**
     * Send key to email to change password
     *
     * @param email user email
     * @param locale required locale of email message
     * @return key which was sent to email
     * @throws ServiceException if database access error occurs
     */
    Optional<String> sendKeyToUpdatePassword(String email, String locale) throws ServiceException;


    /**
     * Update isLoyal field of user
     *
     * @param isLoyal new isLoyal boolean value
     * @param userId user id
     * @return true if user exists and was updated, otherwise false
     * @throws ServiceException if database access error occurs
     */
    boolean updateLoyal(boolean isLoyal, int userId) throws ServiceException;


    /**
     * Add card to user
     *
     * @param id user id
     * @param cardId card id
     * @return true if user and card exist and user was updated
     * @throws ServiceException if database access error occurs
     */
    boolean addCard(int id, int cardId) throws ServiceException;


    /**
     * Delete user by id
     *
     * @param id user id
     * @return true if user was deleted, otherwise false
     * @throws ServiceException if database access error occurs
     */
    boolean delete(int id) throws ServiceException;

}
