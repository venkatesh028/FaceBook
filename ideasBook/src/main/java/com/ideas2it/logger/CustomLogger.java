package com.ideas2it.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Use to log the information and exception.
 *
 * @version 1.0 10-OCT-2022
 * @author Venkatesh TM
 */
public class CustomLogger {
    private Logger logger;

    public CustomLogger(Class<?> className) {
        logger = LogManager.getLogger(className);
    }
    
    /**
     * Logs the information message.
     *
     * @param String - message
     */
    public void info(String message){
        logger.info(message);
    }
    
    /**
     * Logs the warnning message.
     *
     * @param String - message
     */
    public void warn(String message){
        logger.warn(message);
    }
    
    /**
     * Logs the error message.
     *
     * @param String - message
     */
    public void error(String message){
        logger.error(message);
    }
}