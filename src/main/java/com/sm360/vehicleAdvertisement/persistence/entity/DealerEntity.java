/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Dealer details
 * 
 * @author fabrice
 * 
 */

@Entity
@Table(name = "dealer_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealerEntity {
    
    /** @param id The dealer's identifier. */
    @Id
    @GeneratedValue
    private UUID id;

    /** @param name The dealer's name. */
    private String name;
    
    /** @param tierLimit The dealer's tierLimit (number of published listings a dealer can have online.) */
    private int tierLimit;
    

}
