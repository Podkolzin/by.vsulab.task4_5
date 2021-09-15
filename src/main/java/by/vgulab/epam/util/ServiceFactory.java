package by.vgulab.epam.util;

import by.vgulab.epam.dao.OrderDao;
import by.vgulab.epam.dao.TourDao;
import by.vgulab.epam.dao.UserDao;
import by.vgulab.epam.service.OrderService;
import by.vgulab.epam.service.TourService;
import by.vgulab.epam.service.Transaction;
import by.vgulab.epam.service.UserService;

import java.sql.Connection;

public interface ServiceFactory extends AutoCloseable {
    UserDao getUserDao() throws FactoryException;

    TourDao getTourDao() throws FactoryException;

    OrderDao getOrderDao() throws FactoryException;

    Connection getConnection() throws FactoryException;

    UserService getUserService() throws FactoryException;

    TourService getTourService() throws FactoryException;

    OrderService getOrderService() throws FactoryException;


    Transaction getTransaction() throws FactoryException;


}
