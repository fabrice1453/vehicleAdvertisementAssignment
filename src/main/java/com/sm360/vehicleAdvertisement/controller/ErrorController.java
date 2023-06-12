/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.controller;

import com.sm360.vehicleAdvertisement.dto.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fabrice
 */
@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    /**
     * Handles the "/error" endpoint for handling not found errors.
     *
     * @param request The HttpServletRequest object.
     * @return ResponseEntity containing the API response with the appropriate error details.
     */
    @RequestMapping("/error")
    public ResponseEntity<ApiResponse<Void>> handleNotFoundError(HttpServletRequest request) {
        // Create an appropriate API response for the 404 error
        ApiResponse<Void> response = new ApiResponse<>(false, "Endpoint not found", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Returns the path of the error endpoint.
     *
     * @return The path of the error endpoint.
     */
    public String getErrorPath() {
        return "/error";
    }
}
