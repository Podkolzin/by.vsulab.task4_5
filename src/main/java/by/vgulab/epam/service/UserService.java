package by.vgulab.epam.service;

import by.vgulab.epam.domain.User;
import by.vgulab.epam.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> findAll() throws ServiceException;

    User findById(Long id) throws ServiceException;

    void save(User user) throws ServiceException;

    void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException;

    boolean canDelete(Long id) throws ServiceException;

    void delete(Long id) throws ServiceException;

    User readByLoginAndPassword(String login, String password) throws ServiceException;


}
