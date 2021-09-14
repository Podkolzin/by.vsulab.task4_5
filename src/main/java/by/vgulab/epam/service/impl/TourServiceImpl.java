package by.vgulab.epam.service.impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.TourDao;
import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.service.TourService;
import by.vgulab.epam.service.exception.ServiceException;
import by.vgulab.epam.service.exception.TourNotExistsException;

import java.util.List;

public class TourServiceImpl extends BaseService implements TourService {
    private TourDao tourDao;

    public void setUserDao(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    @Override
    public List<Tour> findAll() throws ServiceException {

        try {

            return tourDao.readAll();

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Tour findById(Long id) throws ServiceException {

        try {
            return tourDao.read(id);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void save(Tour tour) throws ServiceException {

        try {
            if(tour.getId() != null){
                tourDao.update(tour);
            } else {
             tourDao.create(tour);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }


    }

    @Override
    public void changePrice(Long tourId, int newPrice) throws ServiceException {

        try {
            getTransaction().start();
            Tour tour = tourDao.read(tourId);
            if (tour != null) {
                tour.setPrice(newPrice);

                tourDao.update(tour);
            } else {
                throw new TourNotExistsException(tourId);
            }
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }


    @Override
    public boolean canDelete(Long id) throws ServiceException {
        try {
            return !tourDao.isUserInitiatesTransfers(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            tourDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
