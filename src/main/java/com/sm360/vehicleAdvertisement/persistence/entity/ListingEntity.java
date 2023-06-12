/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Listing details 
 * 
 * @author fabrice
 * 
 */


@Entity
@Table(name = "listing_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingEntity {
    
    /** @param id The listing's identifier.*/
    @Id
    @GeneratedValue
    private UUID id;
    
    /** @param dealerId The dealer's identifier. */
    private UUID dealerId;
    
    /** @param vehicle The listing's vehicle.*/
    private String vehicle;
    
    /** @param price The listing's price.*/
    private double price;
    
    /** @param createdAt The listing's creation date and time.*/
    private LocalDateTime createdAt;
    
    /** @param state The listing's state. */
    @Enumerated(EnumType.STRING)
    private ListingState state;
    
   
}


