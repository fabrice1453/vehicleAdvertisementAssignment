/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception thrown when the validation of a listing fails.
 * 
 * @author fabri
 *
 */

public class ValidationException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationException.class);

    /**
     * Constructs a new ListingValidationException with the specified detail message.
     *
     * @param message the detail message
     */
    public ValidationException(String message) {
        super(message);
        LOGGER.error("Listing validation failed: {}", message);
    }

    /**
     * Constructs a new ListingValidationException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error("Listing validation failed: {}", message, cause);
    }
}
