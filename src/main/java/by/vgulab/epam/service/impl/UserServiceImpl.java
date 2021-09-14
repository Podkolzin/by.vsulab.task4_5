package by.vgulab.epam.service.impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.UserDao;
import by.vgulab.epam.domain.User;
import by.vgulab.epam.service.UserService;
import by.vgulab.epam.service.exception.ServiceException;
import by.vgulab.epam.service.exception.UserNotExistsException;
import by.vgulab.epam.service.exception.UserPasswordIncorrectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl extends BaseService implements UserService {
    private UserDao userDao;
    private String defaultPassword;
    Object object;
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }


    @Override
    public List<User> findAll() throws ServiceException {
        try {
            log.info("transition to findAll User");
            return userDao.readAll();
        } catch (DaoException e) {
            log.error("transition to" + e.getMessage());
            throw new ServiceException(e);

        }
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            log.info("transition to findById User");
            return userDao.read(id);
        } catch (DaoException e) {
            log.error("Didn't find one" + e.getMessage());
            throw new ServiceException(e);
        }

    }

    @Override
    public void save(User user) throws ServiceException {
        log.info("Beginning to save the user");
        try {
            if (user.getPassword().length() == 0) {
                user.setPassword("1q2w3e");
                log.info("YOU DON'T HAVE A PASSWORD, YOU NEW PASSWORD " + user.getPassword());
            }

            if (user.getId() != null) {
                log.info("UPDATE USER");
                userDao.update(user);
            } else {
                log.info("CREATE USER");
                userDao.create(user);
            }
        } catch (DaoException e) {
            log.error("NOT CREATE USER");
            throw new ServiceException(e);
        }


//        try {
//            log.info("Beginning to save the user");
//            getTransaction().start();
//            if (user.getId() != null) {
//                User storedUser = userDao.read(user.getId());
//                if (storedUser != null) {
//                    user.setPassword(storedUser.getPassword());
//                    if (storedUser.getLogin().equals(user.getLogin()) || userDao.readByLogin(user.getLogin()) == null) {
//                        userDao.update(user);
//                    } else {
//                        log.error("Username not found ");
//                        throw new UserLoginNotUniqueException(user.getLogin());
//                    }
//                } else {
//                    log.error("And such a user does not exist");
//                    throw new UserNotExistsException(user.getId());
//                }
//            } else {
//                user.setPassword(defaultPassword);
//                if (userDao.readByLogin(user.getLogin()) == null) {
//                    Long id = userDao.create(user);
//                    user.setId(id);
//                } else {
//                    log.error("username is not unique");
//                    throw new UserLoginNotUniqueException(user.getLogin());
//                }
//            }
//            getTransaction().commit();
//        } catch (DaoException e) {
//            try {
//                getTransaction().rollback();
//            } catch (ServiceException e1) {
//            }
//            throw new ServiceException(e);
//        } catch (ServiceException e) {
//            try {
//                getTransaction().rollback();
//            } catch (ServiceException e1) {
//            }
//            throw e;
//        }
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException {
        try {
            log.info("let's change the password");
            getTransaction().start();
            User user = userDao.read(userId);
            if (user != null) {

                if (user.getPassword().equals(oldPassword)) {
                    if (newPassword == null) {
                        log.debug("There was no password");
                        newPassword = defaultPassword;
                    }
                    user.setPassword(newPassword);
                    userDao.update(user);
                } else {
                    log.error("Let's provide a good password");
                    throw new UserPasswordIncorrectException(user.getId());
                }
            } else {
                throw new UserNotExistsException(userId);
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
            return !userDao.isUserInitiatesTransfers(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            log.info("Removal started");
            userDao.delete(id);
        } catch (DaoException e) {
            log.error("delete doesn’t work maybe wrong id");
            throw new ServiceException(e);
        }
    }

}
