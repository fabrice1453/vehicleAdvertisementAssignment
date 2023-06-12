/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.exception;

/**
 *
 * Exception thrown when an unknown tier limit handling value is encountered.
 * 
 * @author fabri
 * 
 */
public class UnknownTierLimitHandlingException extends RuntimeException {

    /**
     * Constructs a new UnknownTierLimitHandlingException with the specified error message.
     *
     * @param message the error message
     */
    public UnknownTierLimitHandlingException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new UnknownTierLimitHandlingException with the specified error message and cause.
     *
     * @param message the error message
     * @param cause   the cause of the exception
     */
    public UnknownTierLimitHandlingException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
