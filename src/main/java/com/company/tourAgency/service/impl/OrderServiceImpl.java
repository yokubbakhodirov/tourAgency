package com.company.tourAgency.service.impl;

import com.company.tourAgency.dao.impl.order.OrderDaoImpl;
import com.company.tourAgency.entity.Order;
import com.company.tourAgency.exception.DaoException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl instance;
    private static OrderDaoImpl orderDao;

    private OrderServiceImpl() {
        orderDao = OrderDaoImpl.getInstance();
    }

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(int userId, int tourId, int tourAmount) throws ServiceException {
        try {
            Order order = Order.builder()
                    .userId(userId)
                    .tourId(tourId)
                    .amount(tourAmount)
                    .build();
            return orderDao.insert(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Order> findById(int id) throws ServiceException {
        try {
            return orderDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Order> findLastAdded() throws ServiceException {
        try {
            return orderDao.findLastAdded();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Order t) throws ServiceException {
        throw new UnsupportedOperationException("Update operation of order is not supported");
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return orderDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
