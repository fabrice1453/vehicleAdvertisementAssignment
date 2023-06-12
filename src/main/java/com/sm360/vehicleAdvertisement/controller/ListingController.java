/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.controller;

import com.sm360.vehicleAdvertisement.dto.ListingDTO;
import com.sm360.vehicleAdvertisement.dto.ListingMapper;
import com.sm360.vehicleAdvertisement.dto.api.ApiResponse;
import com.sm360.vehicleAdvertisement.service.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 
 * Controller that exposes services for vehicle listings
 * 
 * @author fabrice
 * 
 */
@RestController
@RequestMapping(ConstantsController.APPLICATION_BASE_PATH + "/listings")
public class ListingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListingController.class);

    @Autowired
    private  ListingService listingService;
    
     /** Mapper for {@link ListingEntity} class */
    @Autowired
    private  ListingMapper listingMapper;

    /**
     * Create a listing.
     *
     * @param listing The Listing object to be created.
     * @return The ResponseEntity with the ApiResponse containing the created Listing.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<ListingDTO>> createListing(@RequestBody ListingDTO listing) {
        LOGGER.info("Creating a new listing");

        ListingDTO createdListing = listingService.createListing(listing);
        ApiResponse<ListingDTO> response = new ApiResponse<>(true, "Listing created successfully", createdListing);
        return ResponseEntity.ok(response);
    }

    /**
     * Update a listing.
     *
     * @param listingId The ID of the listing to be updated.
     * @param listing   The updated Listing object.
     * @return The ResponseEntity with the ApiResponse containing the updated Listing.
     */
    @PutMapping("/{listingId}")
    public ResponseEntity<ApiResponse<ListingDTO>> updateListing(@PathVariable UUID listingId, @RequestBody ListingDTO listing) {
        LOGGER.info("Updating listing with ID: {}", listingId);

        listing.setId(listingId);
        ListingDTO updatedListing = listingService.updateListing(listing);
        ApiResponse<ListingDTO> response = new ApiResponse<>(true, "Listing updated successfully", updatedListing);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all listings of a dealer with a given state.
     *
     * @param dealerId The ID of the dealer.
     * @param state    The state of the listings (draft or published).
     * @return The ResponseEntity with the ApiResponse containing the list of listings.
     */
    @GetMapping("/{dealerId}/{state}")
    public ResponseEntity<ApiResponse<List<ListingDTO>>> getListingsByDealerAndState(
            @PathVariable UUID dealerId, @PathVariable String state) {
        LOGGER.info("Fetching listings for dealer with ID: {} and state: {}", dealerId, state);

        List<ListingDTO> listings = listingService.getListingsByDealerAndState(dealerId, listingMapper.convertStringToListingState(state));
        ApiResponse<List<ListingDTO>> response = new ApiResponse<>(true, "Listings fetched successfully", listings);
        return ResponseEntity.ok(response);
    }

    /**
     * Publish a listing.
     *
     * @param listingId The ID of the listing to be published.
     * @param tierLimitHandlingValue  The handling option for dealing with the situation when the tier limit is reached.
     * @return The ResponseEntity with the ApiResponse containing the published Listing.
     */
    @PostMapping("/{listingId}/{tierLimitHandlingValue}/publish")
    public ResponseEntity<ApiResponse<ListingDTO>> publishListing(@PathVariable UUID listingId, @PathVariable String tierLimitHandlingValue) {
        LOGGER.info("Publishing listing with ID: {}", listingId);

        ListingDTO publishedListing = listingService.publishListing(listingId, listingMapper.convertStringToTierLimitHandling(tierLimitHandlingValue));
        ApiResponse<ListingDTO> response = new ApiResponse<>(true, "Listing published successfully", publishedListing);
        return ResponseEntity.ok(response);
    }

    /**
     * Unpublish a listing.
     *
     * @param listingId The ID of the listing to be unpublished.
     * @return The ResponseEntity with the ApiResponse containing the unpublished Listing.
     */
    @PostMapping("/{listingId}/unpublish")
    public ResponseEntity<ApiResponse<ListingDTO>> unpublishListing(@PathVariable UUID listingId) {
        LOGGER.info("Unpublishing listing with ID: {}", listingId);

        ListingDTO unpublishedListing = listingService.unpublishListing(listingId);
        ApiResponse<ListingDTO> response = new ApiResponse<>(true, "Listing unpublished successfully", unpublishedListing);
        return ResponseEntity.ok(response);
    }

}
