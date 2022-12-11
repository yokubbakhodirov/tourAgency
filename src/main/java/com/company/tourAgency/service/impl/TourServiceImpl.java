package com.company.tourAgency.service.impl;

import com.company.tourAgency.dao.impl.tour.TourDaoImpl;
import com.company.tourAgency.entity.Tour;
import com.company.tourAgency.entity.enums.TourType;
import com.company.tourAgency.exception.DaoException;
import com.company.tourAgency.exception.ServiceException;
import com.company.tourAgency.service.TourService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TourServiceImpl implements TourService {
    private static final Logger logger = LogManager.getLogger();

    private static TourServiceImpl instance;
    private static TourDaoImpl tourDao;

    private TourServiceImpl() {
        tourDao = TourDaoImpl.getInstance();
    }

    public static TourServiceImpl getInstance() {
        if (instance == null) {
            instance = new TourServiceImpl();
        }
        return instance;
    }


    @Override
    public boolean insert(String name, String type, String description,
                          Integer discount, String date, double price, String imagePath) throws ServiceException {
        Optional<Tour> tourByName = findByName(name);
        if (tourByName.isPresent()) {
            return false;
        }
        try {
            TourType tourType;
            try {
                tourType = TourType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.error("Invalid tour type passed: {}", e.getMessage());
                return false;
            }
            String[] dateTimeArray = date.split("T");
            String dateTime = dateTimeArray[0] + " " + dateTimeArray[1]+ ":00";
            Tour tour = Tour.builder()
                    .name(name)
                    .type(tourType)
                    .description(description)
                    .discount(discount)
                    .date(Timestamp.valueOf(dateTime))
                    .price(price)
                    .imagePath(imagePath)
                    .build();

            return tourDao.insert(tour);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Tour> findById(int id) throws ServiceException {
        try {
            return tourDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Tour> findByName(String name) throws ServiceException {
        try {
            return tourDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Tour, Integer> findTourByUserId(int userId) throws ServiceException {
        try {
            return tourDao.findTourByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tour> findAll() throws ServiceException {
        try {
            return tourDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return tourDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
