/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.controller;

import com.sm360.vehicleAdvertisement.dto.DealerDTO;
import com.sm360.vehicleAdvertisement.dto.api.ApiResponse;
import com.sm360.vehicleAdvertisement.service.DealerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controller for managing Dealers CRUD
 * @author fabrice 
 */
@RestController
@RequestMapping(ConstantsController.APPLICATION_BASE_PATH + "/dealers")
@Component
public class DealerController {

    private static final Logger logger = LoggerFactory.getLogger(DealerController.class);
    @Autowired
    private DealerService dealerService;

    /**
     * Retrieves all dealers.
     *
     * @return the list of dealers
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<DealerDTO>>> getAllDealers() {
        List<DealerDTO> dealers = dealerService.getAllDealers();
        ApiResponse<List<DealerDTO>> response = new ApiResponse<>(true, "Success", dealers);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a dealer by its ID.
     *
     * @param id the dealer ID
     * @return the dealer entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DealerDTO>> getDealerById(@PathVariable UUID id) {
        logger.info("Fetching dealer with ID: {}", id);
        DealerDTO dealer = dealerService.getDealerById(id);
        ApiResponse<DealerDTO> response = new ApiResponse<>(true, "Success", dealer);
        return ResponseEntity.ok(response);
    }

    /**
     * Creates a new dealer.
     *
     * @param dealer the dealer entity to create
     * @return the created dealer entity
     */
    @PostMapping
    public ResponseEntity<ApiResponse<DealerDTO>> createDealer(@RequestBody DealerDTO dealer) {
        logger.info("Creating a new dealer");
        DealerDTO createdDealer = dealerService.createDealer(dealer);
        ApiResponse<DealerDTO> response = new ApiResponse<>(true, "Dealer created", createdDealer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Updates an existing dealer.
     *
     * @param id             the ID of the dealer to update
     * @param updatedDealer the updated dealer entity
     * @return the updated dealer entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DealerDTO>> updateDealer(@PathVariable UUID id, @RequestBody DealerDTO updatedDealer) {
        logger.info("Updating dealer with ID: {}", id);
        DealerDTO updated = dealerService.updateDealer(id, updatedDealer);
        ApiResponse<DealerDTO> response = new ApiResponse<>(true, "Dealer updated", updated);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a dealer by its ID.
     *
     * @param id the ID of the dealer to delete
     * @return the success message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDealer(@PathVariable UUID id) {
        logger.info("Deleting dealer with ID: {}", id);
        dealerService.deleteDealer(id);
        ApiResponse<String> response =  ApiResponse.success("Dealer deleted");
        return ResponseEntity.ok(response);
    }
}
