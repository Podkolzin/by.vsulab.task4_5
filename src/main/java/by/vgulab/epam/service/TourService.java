package by.vgulab.epam.service;

import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.service.exception.ServiceException;

import java.util.List;

public interface TourService {

    List<Tour> findAll() throws ServiceException;

    Tour findById(Long id) throws ServiceException;

    void save(Tour tour) throws ServiceException;

    void changePrice(Long tourId, int newPrice) throws ServiceException;

    boolean canDelete(Long id) throws ServiceException;

    void delete(Long id) throws ServiceException;

    }
