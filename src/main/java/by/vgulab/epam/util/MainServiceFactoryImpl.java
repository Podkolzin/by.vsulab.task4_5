package by.vgulab.epam.util;

import by.vgulab.epam.dao.OrderDao;
import by.vgulab.epam.dao.impl.OrderDaoImpl;
import by.vgulab.epam.dao.impl.TourDaoImpl;
import by.vgulab.epam.dao.impl.UserDaoImpl;
import by.vgulab.epam.dao.TourDao;
import by.vgulab.epam.dao.UserDao;
import by.vgulab.epam.pool.ConnectionPool;
import by.vgulab.epam.pool.ConnectionPoolException;
import by.vgulab.epam.service.OrderService;
import by.vgulab.epam.service.TourService;
import by.vgulab.epam.service.Transaction;
import by.vgulab.epam.service.UserService;
import by.vgulab.epam.service.impl.OrderServiceImpl;
import by.vgulab.epam.service.impl.TourServiceImpl;
import by.vgulab.epam.service.impl.TransactionImpl;
import by.vgulab.epam.service.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class MainServiceFactoryImpl implements ServiceFactory {
    private Connection connection;

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;
    }

    @Override
    public TourDao getTourDao() throws FactoryException {
        TourDaoImpl tourDao = new TourDaoImpl();
        tourDao.setConnection(getConnection());
        return tourDao;
    }
    @Override
    public OrderDao getOrderDao() throws FactoryException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.setConnection(getConnection());
        return orderDao;
    }

    @Override
    public Connection getConnection() throws FactoryException {
        if (connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (ConnectionPoolException e) {
                throw new FactoryException(e);
            }
        }
        return connection;
    }
    @Override
    public Transaction getTransaction() throws FactoryException {
        TransactionImpl transaction = new TransactionImpl();
        transaction.setConnection(getConnection());
        return transaction;
    }

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
      //  userService.setDefaultPassword("12345");
        userService.setTransaction(getTransaction());
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public TourService getTourService() throws FactoryException {
        TourServiceImpl tourService = new TourServiceImpl();
        tourService.setTransaction(getTransaction());
        tourService.setTourDao(getTourDao());

        return tourService;
    }

    public OrderService getOrderService() throws FactoryException {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setTransaction(getTransaction());
        orderService.setOrderDao(getOrderDao());

        return orderService;
    }

    @Override
    public void close() throws Exception {
        {
            try {
                connection.close();
                connection = null;
            } catch (Exception e) {
            }
        }

    }
}
