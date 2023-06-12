/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception thrown when an unauthorized access is attempted.
 * 
 *  @author fabri
 * 
 */
public class UnauthorizedAccessException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnauthorizedAccessException.class);

    /**
     * Constructs a new UnauthorizedAccessException with the specified message.
     *
     * @param message the detail message
     */
    public UnauthorizedAccessException(String message) {
        super(message);
        LOGGER.error("Unauthorized access: {}", message);
    }

    /**
     * Constructs a new UnauthorizedAccessException with the specified message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error("Unauthorized access: {}", message, cause);
    }
    
}
