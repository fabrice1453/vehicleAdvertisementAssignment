/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sm360.vehicleAdvertisement.service;

import com.sm360.vehicleAdvertisement.dto.ListingDTO;
import com.sm360.vehicleAdvertisement.dto.ListingState;
import com.sm360.vehicleAdvertisement.dto.TierLimitHandling;
import java.util.List;
import java.util.UUID;

/**
 *
 * Service interface for managing Listing entities.
 *
 * @author fabrice
 * 
 */

public interface ListingService {
    
    /**
     * Creates a new listing.
     *
     * @param listing The listing to create.
     * @return The created listing.
     */
    ListingDTO createListing(ListingDTO listing);

    /**
     * Updates an existing listing.
     *
     * @param listing The listing to update.
     * @return The updated listing.
     */
    ListingDTO updateListing(ListingDTO listing);

    /**
     * Retrieves a list of listings based on the dealer ID and listing state.
     *
     * @param dealerId The ID of the dealer.
     * @param state The listing state.
     * @return The list of matching listings.
     */
    List<ListingDTO> getListingsByDealerAndState(UUID dealerId, ListingState state);

    /**
     * Publishes a listing with the specified ID.
     *
     * @param listingId The ID of the listing to publish.
     * @param tierLimitHandlingValue  The handling option for dealing with the situation when the tier limit is reached.
     * @return The published listing.
     */
    ListingDTO publishListing(UUID listingId , TierLimitHandling tierLimitHandlingValue);

    /**
     * Unpublishes a listing with the specified ID.
     *
     * @param listingId The ID of the listing to unpublish.
     * @return The unpublished listing.
     */
    ListingDTO unpublishListing(UUID listingId);
}


