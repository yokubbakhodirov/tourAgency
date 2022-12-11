package com.company.tourAgency.utils.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConnectionPool stores and gives connections. Before using it,
 * fill up database.properties file to initialize database. After getting connection, make sure
 * you don't close it and don't forget to release it so that it can be used again.
 */
public class ConnectionPool {

    private static Logger logger = LogManager.getLogger();
    private static Lock lock = new ReentrantLock();
    private static ConnectionPool instance;

    // Properties
    private static final Properties properties = new Properties();
    private static final String DB_FILE_PROPERTY = "properties/database.properties";
    private static final String URL_PROPERTY = "url";
    private static final String USER_PROPERTY = "user";
    private static final String PASSWORD_PROPERTY = "password";
    private static final String DRIVER_PROPERTY = "driver";
    private static final String POOL_SIZE_PROPERTY = "pool_size";
    private static final String URL_VALUE;
    private static final int DEFAULT_POOL_SIZE_VALUE = 5;
    private static final int POOL_SIZE_VALUE;

    private BlockingDeque<Connection> free = new LinkedBlockingDeque<>(POOL_SIZE_VALUE);
    private BlockingDeque<Connection> used = new LinkedBlockingDeque<>(POOL_SIZE_VALUE);

    // Properties and driver initialization
    static {
        String driverValue = null;
        try (InputStream propertiesStream = ConnectionPool.class.getClassLoader().getResourceAsStream(DB_FILE_PROPERTY)) {
            Properties fileProperties = new Properties();
            fileProperties.load(propertiesStream);

            URL_VALUE = fileProperties.getProperty(URL_PROPERTY);
            System.out.println("URL_VALUE = " + URL_VALUE);
            properties.put(USER_PROPERTY, fileProperties.getProperty(USER_PROPERTY));
            properties.put(PASSWORD_PROPERTY, fileProperties.getProperty(PASSWORD_PROPERTY));
            System.out.println("properties = " + properties);

            driverValue = fileProperties.getProperty(DRIVER_PROPERTY);
            Class.forName(driverValue);
            logger.debug("Driver registered: {}", driverValue);

            int poolSize = DEFAULT_POOL_SIZE_VALUE;
            String poolSizeStr = fileProperties.getProperty(POOL_SIZE_PROPERTY);
            try {
                poolSize = Integer.parseInt(poolSizeStr);
            } catch (NumberFormatException e) {
                logger.error("Invalid pool size parameter in database properties file: {}", poolSizeStr);
            }

            POOL_SIZE_VALUE = poolSize;
            logger.debug("Pool size: {}", POOL_SIZE_VALUE);
        } catch (IOException e) {
            logger.error("Cannot open properties file: {}", DB_FILE_PROPERTY);
            throw new ExceptionInInitializerError("Cannot open properties file: " + DB_FILE_PROPERTY);
        } catch (ClassNotFoundException e) {
            logger.error("Error loading driver: {}", driverValue);
            throw new ExceptionInInitializerError("Error loading driver: " + driverValue);
        }
    }

    private ConnectionPool() {
        Connection connection;
        for (int i = 0; i < POOL_SIZE_VALUE; i++) {
            try {
                connection = DriverManager.getConnection(URL_VALUE, properties);
                free.put(connection);
            } catch (SQLException | InterruptedException e) {
                logger.error("Error while instantiating connection pool: {}", e.getMessage());
                throw new ExceptionInInitializerError("Error while instantiating connection pool: {}"
                        + e.getMessage());
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
                logger.debug("Connection pool instance created");
            }
            lock.unlock();
        }

        return instance;
    }

    public void releaseConnection(Connection connection) {
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
            free.put(connection);
            used.remove(connection);
        } catch (InterruptedException e) {
            logger.error("Thread killed while waiting");
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            logger.error("Failed to set auto commit");
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = free.take();
            connection.setAutoCommit(true);
            used.put(connection);
        } catch (InterruptedException e) {
            logger.error("Thread killed while waiting");
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            logger.error("Failed to set auto commit");
        }

        return connection;
    }

    public void destroy() {
        for (int i = 0; i < POOL_SIZE_VALUE; i++) {
            try {
              free.take().close();
            } catch (SQLException e) {
                logger.error("Cannot close connection: " + e.getMessage());
            } catch (InterruptedException e) {
                logger.error("Thread killed while waiting");
                Thread.currentThread().interrupt();
            }
        }

        deregisterDriver();
    }

    public void deregisterDriver() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver;
        while (drivers.hasMoreElements()) {
            driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Cannot deregister driver: {}", e.getMessage());
            }
        }
    }
}
