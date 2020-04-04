package by.epam.jwd.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.dao.db_connection.ConnectionPool;
import by.epam.jwd.dao.db_connection.ConnectionPoolException;

@WebListener
public class Listener implements ServletContextListener {
	private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.initPool();
        } catch (ConnectionPoolException e) {
           LOGGER.error("Failed to initialize listener");
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.dispose();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Failed to destroy connection pool");
        }
    }
}