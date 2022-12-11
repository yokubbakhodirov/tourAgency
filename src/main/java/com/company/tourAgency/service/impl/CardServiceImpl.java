package com.company.tourAgency.service.impl;

import com.company.tourAgency.dao.impl.card.CardDaoImpl;
import com.company.tourAgency.entity.Card;
import com.company.tourAgency.exception.DaoException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.CardService;

import java.util.Optional;

public class CardServiceImpl implements CardService {
    private static CardServiceImpl instance;
    private static CardDaoImpl cardDao;

    private CardServiceImpl() {
        cardDao = CardDaoImpl.getInstance();
    }

    public static CardServiceImpl getInstance() {
        if (instance == null) {
            instance = new CardServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(int userId, String cardNumber) throws ServiceException {
        try {
            Card card = Card.builder()
                    .cardNumber(cardNumber)
                    .build();
            return cardDao.insert(userId, card);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Card> findById(int id) throws ServiceException {
        try {
            return cardDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Card> findByCardNumber(String cardNumber) throws ServiceException {
        try {
            return cardDao.findByCardNumber(cardNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Card card) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return cardDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
