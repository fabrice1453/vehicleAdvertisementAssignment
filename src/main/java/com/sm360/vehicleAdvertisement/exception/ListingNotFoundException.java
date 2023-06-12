/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception thrown when a listing is not found.
 * 
 * @author fabrice
 *
 */

public class ListingNotFoundException extends RuntimeException {
    /**
     * Logger instance for logging error messages related to ListingNotFoundException.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ListingNotFoundException.class);
         
   /**
     * Constructs a new ListingNotFoundException with the specified message.
     *
     * @param message the detail message
     */
    public ListingNotFoundException(String message) {
        super(message);
        LOGGER.error("Listing not found: {}", message);
    }

    /**
     * Constructs a new ListingNotFoundException with the specified message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public ListingNotFoundException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error("Listing not found: {}", message, cause);
    }
}
