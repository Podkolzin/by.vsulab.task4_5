package by.vgulab.epam.util;

import by.vgulab.epam.dao.Impl.UserDaoImpl;
import by.vgulab.epam.dao.UserDao;
import by.vgulab.epam.service.UserService;
import by.vgulab.epam.service.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class MainServiceFactoryImpl implements ServiceFactory{
    private Connection connection;

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;    }

    @Override
    public Connection getConnection() throws FactoryException {
        if(connection == null) {
            try {
                connection = Connector.getConnection();
            } catch(SQLException e) {
                throw new FactoryException(e);
            }
        }
        return connection;
    }

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
//        userService.setDefaultPassword("12345");
//        userService.setTransaction(getTransaction());
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public void close() throws Exception {{
        try {
            connection.close();
            connection = null;
        } catch(Exception e) {}
    }

    }
}
