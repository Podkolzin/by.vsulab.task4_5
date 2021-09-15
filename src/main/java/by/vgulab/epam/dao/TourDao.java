package by.vgulab.epam.dao;

import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.domain.User;

import java.util.List;

public interface TourDao extends Dao<Tour> {

    List<Tour> readAll() throws DaoException;

    Tour readByCountry(String county) throws DaoException;

    boolean isTourInitiatesTransfers(Long id) throws DaoException;

}
