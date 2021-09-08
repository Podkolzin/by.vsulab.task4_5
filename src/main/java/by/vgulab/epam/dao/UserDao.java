package by.vgulab.epam.dao;

import by.vgulab.epam.domain.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    List<User> readAll() throws DaoException;

    boolean isUserInitiatesTransfers(Long id) throws DaoException;

}
