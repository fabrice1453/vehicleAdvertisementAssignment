/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.controller;

import com.sm360.vehicleAdvertisement.dto.api.ApiResponse;
import com.sm360.vehicleAdvertisement.exception.DealerNotFoundException;
import com.sm360.vehicleAdvertisement.exception.InvalidListingStateException;
import com.sm360.vehicleAdvertisement.exception.ListingNotFoundException;
import com.sm360.vehicleAdvertisement.exception.TierLimitExceededException;
import com.sm360.vehicleAdvertisement.exception.UnauthorizedAccessException;
import com.sm360.vehicleAdvertisement.exception.UnknownTierLimitHandlingException;
import com.sm360.vehicleAdvertisement.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 *
 * Global exception handler for handling unhandled exceptions in the application.
 * It provides a centralized place to handle exceptions and return appropriate error responses.
 * 
 * @author fabrice
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Exception handler for handling unhandled exceptions.
     *
     * @param ex the unhandled exception
     * @return the response entity with the error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        LOGGER.error("Unhandled exception occurred", ex);

        ApiResponse<Void> response = ApiResponse.error("An error occurred if the problem persists please contact customer service");
//        ApiResponse<Void> response = ApiResponse.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    // Exclude specific exceptions from being handled by GlobalExceptionHandler
    @ExceptionHandler({ValidationException.class, UnknownTierLimitHandlingException.class, UnauthorizedAccessException.class,
                        TierLimitExceededException.class,ListingNotFoundException.class,  InvalidListingStateException.class, 
                            DealerNotFoundException.class})
    public void handleCustomExceptions() {
        // These exceptions will be caught by CustomExceptionHandler
    }
}
