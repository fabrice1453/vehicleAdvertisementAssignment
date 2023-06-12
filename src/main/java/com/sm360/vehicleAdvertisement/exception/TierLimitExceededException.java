/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception thrown when the tier limit is exceeded.
 * 
 * @author fabri
 * 
 */
public class TierLimitExceededException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(TierLimitExceededException.class);

    /**
     * Constructs a new TierLimitExceededException with the specified message.
     *
     * @param message the detail message
     */
    public TierLimitExceededException(String message) {
        super(message);
        LOGGER.error("Tier limit exceeded: {}", message);
    }

    /**
     * Constructs a new TierLimitExceededException with the specified message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public TierLimitExceededException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error("Tier limit exceeded: {}", message, cause);
    }
    
}
