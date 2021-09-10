package by.vgulab.epam.dao;

import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.domain.User;

import java.util.List;

public interface TourDao extends Dao<Tour> {

    List<Tour> readAll() throws DaoException;

    User readByCountry(String county) throws DaoException;

    boolean isUserInitiatesTransfers(Long id) throws DaoException;

}
