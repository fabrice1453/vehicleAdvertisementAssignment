/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.persistence.repository;

/**
 * Constants for SQL queries related to the Listing entity.
 * These constants can be used in repositories or other classes where SQL queries are needed.
 * 
 * @author fabrice
 * 
 */
public final class ListingQueryConstants {
    
    public static final String COUNT_LISTINGS_BY_DEALER = "SELECT CASE WHEN (SELECT COUNT(l) FROM ListingEntity l WHERE l.dealerId = :dealerId and l.state = 'PUBLISHED') >= d.tierLimit THEN true ELSE false END FROM DealerEntity d WHERE d.id = :dealerId";
    
    public static final String  RETRIEVE_OLDEST_PUBLISHED_LISTING = "SELECT l FROM ListingEntity  l WHERE l.dealerId = :dealerId AND l.state = 'PUBLISHED' ORDER BY l.createdAt ASC limit 1";
    
    private ListingQueryConstants() {
        // Private constructor to prevent instantiation
    }
}

