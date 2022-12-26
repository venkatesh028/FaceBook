package com.ideas2it.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.logger.CustomLogger;

public class DatabaseConfiguration {
    private static CustomLogger logger = new CustomLogger(DatabaseConnection.class);
    public static SessionFactory sessionFactory = null;
    
    private DatabaseConfiguration() {}
    
    public static SessionFactory getSessionFactory() {
        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
        } catch (Exception exception) {
            logger.error("from DatabaseConfiguration : " + exception.getMessage());
        }
        return sessionFactory;
    }           
}