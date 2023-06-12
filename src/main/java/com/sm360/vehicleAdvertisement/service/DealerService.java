/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sm360.vehicleAdvertisement.service;

import com.sm360.vehicleAdvertisement.dto.DealerDTO;
import com.sm360.vehicleAdvertisement.persistence.entity.DealerEntity;
import java.util.List;
import java.util.UUID;

/**
 *
 * Service interface for managing Dealer entities.
 * 
 * @author fabrice
 */

public interface DealerService {

    /**
     * Retrieves all dealers.
     *
     * @return the list of dealers
     */
    List<DealerDTO> getAllDealers();

    /**
     * Retrieves a dealer by its ID.
     *
     * @param id the dealer ID
     * @return the dealer entity
     * @throws DealerNotFoundException if the dealer is not found
     */
    DealerDTO getDealerById(UUID id);

    /**
     * Creates a new dealer.
     *
     * @param dealer the dealer entity to create
     * @return the created dealer entity
     */
    DealerDTO createDealer(DealerDTO dealer);

    /**
     * Updates an existing dealer.
     *
     * @param id             the ID of the dealer to update
     * @param updatedDealer the updated dealer entity
     * @return the updated dealer entity
     * @throws DealerNotFoundException if the dealer is not found
     */
    DealerDTO updateDealer(UUID id, DealerDTO updatedDealer);

    /**
     * Deletes a dealer by its ID.
     *
     * @param id the ID of the dealer to delete
     * @throws DealerNotFoundException if the dealer is not found
     */
    void deleteDealer(UUID id);
}
