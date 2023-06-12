/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception thrown when a listing is in an invalid state.
 *
 * @author fabrice
 *
 */

public class InvalidListingStateException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidListingStateException.class);

    /**
     * Constructs a new InvalidListingStateException with the specified message.
     *
     * @param message the detail message
     */
    public InvalidListingStateException(String message) {
        super(message);
        LOGGER.error("Invalid listing state: {}", message);
    }

    /**
     * Constructs a new InvalidListingStateException with the specified message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public InvalidListingStateException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error("Invalid listing state: {}", message, cause);
    }
    
}
