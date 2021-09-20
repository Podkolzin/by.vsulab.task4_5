package by.vgulab.epam.controller;

import by.vgulab.epam.pool.ConnectionPool;
import by.vgulab.epam.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String jdbcDriver = context.getInitParameter("jdbc-driver");
        String jdbcUrl = context.getInitParameter("jdbc-url");
        String jdbcUsername = context.getInitParameter("jdbc-username");
        String jdbcPassword = context.getInitParameter("jdbc-password");
        int minSize = Integer.parseInt(context.getInitParameter("minSize"));
        int maxSize = Integer.parseInt(context.getInitParameter("manSize"));
        int connectTimeOut = Integer.parseInt(context.getInitParameter("connectTimeOut"));

        try {
            logger.info("Connector was initialized,\njdbc-driver = {},\njdbc-url = {},\njdbc-username = {}\njdbc-password = {}", jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, minSize, maxSize, connectTimeOut);
            ConnectionPool.getInstance().init(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, minSize, maxSize, connectTimeOut);
        } catch (ConnectionPoolException e) {
            logger.fatal("Can't initialize class {}", ConnectionPool.class.getName(), e);
            e.printStackTrace();
        }
        //  Connector.init(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, minSize, maxSize, connectTimeOut);
//            logger.info("Connector was initialized,\njdbc-driver = {},\njdbc-url = {},\njdbc-username = {}\njdbc-password = {}", jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, minSize, maxSize, connectTimeOut);
//        } catch(ClassNotFoundException e) {
//            logger.fatal("Can't initialize class {}", Connector.class.getName(), e);

    }
}
