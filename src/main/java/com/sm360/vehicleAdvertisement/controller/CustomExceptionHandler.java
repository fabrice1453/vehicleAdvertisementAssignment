/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.controller;

import com.sm360.vehicleAdvertisement.exception.DealerNotFoundException;
import com.sm360.vehicleAdvertisement.exception.InvalidListingStateException;
import com.sm360.vehicleAdvertisement.exception.ListingNotFoundException;
import com.sm360.vehicleAdvertisement.exception.ValidationException;
import com.sm360.vehicleAdvertisement.exception.TierLimitExceededException;
import com.sm360.vehicleAdvertisement.exception.UnauthorizedAccessException;
import com.sm360.vehicleAdvertisement.dto.api.ApiResponse;
import com.sm360.vehicleAdvertisement.exception.UnknownTierLimitHandlingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * The CustomExceptionHandler class handles custom exceptions and formats the error response as an API response.
 * @author fabrice
 */



@RestControllerAdvice
public class CustomExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    /**
     * Exception handler for ListingNotFoundException.
     *
     * @param ex the ListingNotFoundException instance
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(ListingNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleListingNotFoundException(ListingNotFoundException ex) {
        LOGGER.error("Listing not found: {}", ex.getMessage());

        ApiResponse<Void> response =  ApiResponse.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for InvalidListingStateException.
     *
     * @param ex the InvalidListingStateException instance
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(InvalidListingStateException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidListingStateException(InvalidListingStateException ex) {
        LOGGER.error("Invalid listing state: {}", ex.getMessage());

        ApiResponse<Void> response =  ApiResponse.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for TierLimitExceededException.
     *
     * @param ex the TierLimitExceededException instance
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(TierLimitExceededException.class)
    public ResponseEntity<ApiResponse<Void>> handleTierLimitExceededException(TierLimitExceededException ex) {
        LOGGER.error("Tier limit exceeded: {}", ex.getMessage());

        ApiResponse<Void> response =  ApiResponse.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /**
     * Exception handler for ListingValidationException.
     *
     * @param ex the ListingValidationException instance
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleListingValidationException(ValidationException ex) {
        LOGGER.error("Listing validation failed: {}", ex.getMessage());

        ApiResponse<Void> response =  ApiResponse.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Exception handler for UnauthorizedAccessException.
     *
     * @param ex the UnauthorizedAccessException instance
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        LOGGER.error("Unauthorized access: {}", ex.getMessage());

        ApiResponse<Void> response =  ApiResponse.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    
    
    /**
     * Exception handler for DealerNotFoundException.
     *
     * @param ex the DealerNotFoundException instance
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(DealerNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> DealerNotFoundException(DealerNotFoundException ex) {
            LOGGER.error("Dealer Not found {}", ex.getMessage());

        ApiResponse<Void> response =  ApiResponse.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
     /**
     * Exception handler for DealerNotFoundException.
     *
     * @param ex the DealerNotFoundException instance
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(UnknownTierLimitHandlingException.class)
    public ResponseEntity<ApiResponse<Void>> UnknownTierLimitHandlingException(UnknownTierLimitHandlingException ex) {
        LOGGER.error(" Unknown Tier Limit: {}", ex.getMessage());

        ApiResponse<Void> response =  ApiResponse.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    
    /**
     * Handles the NotFoundException and returns an error response with the appropriate HTTP status and message.
     *
     * @param ex The NotFoundException that was thrown.
     * @return A ResponseEntity containing the error response.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(NotFoundException ex) {
        
        ApiResponse<Void> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
}
