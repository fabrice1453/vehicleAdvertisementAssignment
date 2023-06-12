/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Exception class representing a Dealer not found error.
 * This exception can be thrown when a Dealer is not found in the system.
 * @author fabri
 */

public class DealerNotFoundException extends RuntimeException {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DealerNotFoundException.class);

    /**
     * Constructs a new DealerNotFoundException with the specified error message.
     *
     * @param message The error message explaining the cause of the exception.
     */
    public DealerNotFoundException(String message) {
        super(message);
    }
    
    
    /**
     * Constructs a new DealerNotFoundException with the specified message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public DealerNotFoundException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error("Dealer not found: {}", message, cause);
    }
}
