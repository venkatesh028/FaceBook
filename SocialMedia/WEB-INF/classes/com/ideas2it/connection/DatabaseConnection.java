package com.ideas2it.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ideas2it.constant.Constants;
import com.ideas2it.logger.CustomLogger;

/**
 * Implements logic to create the sql databse connection
 *
 * @version 1.0 01-Nov-2022
 * @author Venkatesh TM
 */
public class DatabaseConnection {
    private static Connection connection = null;
    private static CustomLogger logger = new CustomLogger(DatabaseConnection.class);

    private DatabaseConnection() {
        try {
            Class.forName(Constants.DRIVER_LINK);
            connection = DriverManager.getConnection(Constants.LINK, Constants.USERNAME, Constants.PASSWORD);
        } catch (Exception sqlException) {
            logger.error(sqlException.getMessage());
        }
    }

    /**
     * Gets the connection for mysql database
     */
    public static Connection getConnection() {
        try {
            if (null == connection || connection.isClosed()) {
                DatabaseConnection databaseConnection = new DatabaseConnection();
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        }

        return connection;
    }
    
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        }
    }
}