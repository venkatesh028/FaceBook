package com.ideas2it.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook", "root", "Venkatesh@09");
        } catch (SQLException sqlException) {
            System.out.println("Connection Not Created");
        }
    }

    public static Connection getConnection() {
        try {
            if (null == connection || connection.isClosed()) {
                DatabaseConnection databaseConnection = new DatabaseConnection();
            }
        } catch (SQLException sqlE) {
            System.out.println("Error");
        }
        return connection;
    }
}