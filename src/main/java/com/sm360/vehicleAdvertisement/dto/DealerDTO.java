/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * 
 * DTO (Data Transfer Object) representing a Dealer.
 * It contains the information related to a dealer entity that needs to be transferred over the network.
 *
 * @author fabri
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealerDTO {
    /**
     * Unique identifier of the dealer.
     */
    private UUID id;

    /**
     * Name of the dealer.
     */
    private String name;
        
    /** 
     * @param tierLimit The dealer's tierLimit (number of published listings a dealer can have online.) 
     */
    private int tierLimit;
}

