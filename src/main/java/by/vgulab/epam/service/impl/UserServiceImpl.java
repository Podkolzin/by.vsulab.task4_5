package by.vgulab.epam.service.impl;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.UserDao;
import by.vgulab.epam.domain.User;
import by.vgulab.epam.service.UserService;
import by.vgulab.epam.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl extends BaseServlet implements UserService {
    private UserDao userDao;
    private String defaultPassword;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }


    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public void save(User user) throws ServiceException {

    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException {

    }

    @Override
    public boolean canDelete(Long id) throws ServiceException {
        return false;
    }

    @Override
    public void delete(Long id) throws ServiceException {

    }
}
