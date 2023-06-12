/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sm360.vehicleAdvertisement.persistence.entity.ListingState;
import com.sm360.vehicleAdvertisement.utils.ListingStateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * DTO (Data Transfer Object) representing a Listing.
 * It contains the information related to a listing entity that needs to be transferred over the network.
 * 
 * @author fabrice
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingDTO {
    /**
     * Unique identifier of the listing.
     */
    private UUID id;

    /**
     * Identifier of the dealer who owns the listing.
     */
    private UUID dealerId;

    /**
     * Vehicle associated with the listing.
     */
    private String vehicle;

    /**
     * Price of the listing.
     */
    private double price;

    /**
     * Date and time when the listing was created.
     */
    private LocalDateTime createdAt;

    /**
     * State of the listing (draft or published).
     */
    @JsonDeserialize(using = ListingStateDeserializer.class)
    private ListingState state;

    
}

