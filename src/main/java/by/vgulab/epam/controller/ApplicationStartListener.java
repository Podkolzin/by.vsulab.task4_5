package by.vgulab.epam.controller;

import by.vgulab.epam.util.Connector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ServletContext context = event.getServletContext();
            String jdbcDriver   = context.getInitParameter("jdbc-driver");
            String jdbcUrl      = context.getInitParameter("jdbc-url");
            String jdbcUsername = context.getInitParameter("jdbc-username");
            String jdbcPassword = context.getInitParameter("jdbc-password");
            Connector.init(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
            logger.info("Connector was initialized,\njdbc-driver = {},\njdbc-url = {},\njdbc-username = {}\njdbc-password = {}", jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
        } catch(ClassNotFoundException e) {
            logger.fatal("Can't initialize class {}", Connector.class.getName(), e);
        }
    }
}
