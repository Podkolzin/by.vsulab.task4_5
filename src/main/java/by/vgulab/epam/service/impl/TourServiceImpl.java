package by.vgulab.epam.service.impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.TourDao;
import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.service.TourService;
import by.vgulab.epam.service.exception.ServiceException;
import by.vgulab.epam.service.exception.TourNotExistsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TourServiceImpl extends BaseService implements TourService {
    private static final Logger log = LogManager.getLogger(TourServiceImpl.class);

    private TourDao tourDao;

    public void setUserDao(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    @Override
    public List<Tour> findAll() throws ServiceException {

        try {
            log.info("transition to findAll Tour");
            return tourDao.readAll();

        } catch (DaoException e) {
            log.error("transition to" + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Tour findById(Long id) throws ServiceException {

        try {
            log.info("transition to findById Tour");
            return tourDao.read(id);

        } catch (DaoException e) {
            log.error("Didn't find one" + e.getMessage());
            throw new ServiceException(e);
        }

    }

    @Override
    public void save(Tour tour) throws ServiceException {
        log.info("Beginning to save the Tour");
        try {
            if(tour.getId() != null){
                log.info("UPDATE Tour");
                tourDao.update(tour);
            } else {
                log.info("CREATE TOUR");
             tourDao.create(tour);
            }
        } catch (DaoException e) {
            log.error("NOT CREATE Tour");
            throw new ServiceException(e);
        }


    }

    @Override
    public void changePrice(Long tourId, int newPrice) throws ServiceException {
                log.info("Change Price");
        try {
            getTransaction().start();
            Tour tour = tourDao.read(tourId);
            if (tour != null) {
                tour.setPrice(newPrice);

                tourDao.update(tour);
            } else {
                log.error("Tour not Exists");
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
            log.info("Removal started");
            tourDao.delete(id);
        } catch (DaoException e) {
            log.error("delete doesn’t work maybe wrong id");
            throw new ServiceException(e);
        }
    }
}
