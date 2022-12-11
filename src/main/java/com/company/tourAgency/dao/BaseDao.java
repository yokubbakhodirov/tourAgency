package com.company.tourAgency.dao;

import com.company.tourAgency.entity.AbstractEntity;
import com.company.tourAgency.exception.DaoException;

import java.util.Optional;

/**
 * BASE DAO
 */
public interface BaseDao<T extends AbstractEntity> {
    boolean insert(T t) throws DaoException;
    Optional<T> findById(int id) throws DaoException;
    boolean update(T t) throws DaoException;
    boolean delete(int id) throws DaoException;
}
