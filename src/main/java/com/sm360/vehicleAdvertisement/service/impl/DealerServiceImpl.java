/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.service.impl;

/**
 *
 * Implementation of the DealerService interface that provides operations related to Dealer.
 * 
 * @author fabrice
 */
import com.sm360.vehicleAdvertisement.dto.DealerDTO;
import com.sm360.vehicleAdvertisement.dto.DealerMapper;
import com.sm360.vehicleAdvertisement.exception.DealerNotFoundException;
import com.sm360.vehicleAdvertisement.exception.ValidationException;
import com.sm360.vehicleAdvertisement.persistence.repository.DealerRepository;
import com.sm360.vehicleAdvertisement.service.DealerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DealerServiceImpl implements DealerService {

    /** {@link DealerRepository} bean */
    @Autowired
    private DealerRepository dealerRepository;
    
    /** Mapper for {@link DealerEntity} class */
    @Autowired
    private DealerMapper dealerMapper;
 
    private final Logger logger = LoggerFactory.getLogger(DealerServiceImpl.class);

    /**
     * Retrieves all dealers.
     *
     * @return The list of all dealers.
     */
    @Override
    public List<DealerDTO> getAllDealers() {
        logger.info("Getting all dealers");
        return dealerMapper.mapToDealerDTO(dealerRepository.findAll());
    }

    /**
     * Retrieves a dealer by its ID.
     *
     * @param id The ID of the dealer.
     * @return The dealer with the specified ID.
     * @throws DealerNotFoundException if the dealer is not found.
     */
    @Override
    public DealerDTO getDealerById(UUID id) {
        logger.info("Getting dealer by ID: {}", id);
        return dealerRepository.findById(id)
                .map(dealerMapper::mapToDealerDTO)
                .orElseThrow(() -> new DealerNotFoundException("Dealer not found with ID: " + id));
    }

    /**
     * Creates a new dealer.
     *
     * @param dealer The dealer DTO Object to be Mapped as The Entity to be created.
     * @return The created dealer entity converted as DealerDTO .
     */
    @Override
    public DealerDTO createDealer(DealerDTO dealer) {
        
        logger.info("Creating dealer: {}", dealer);
        // validate dealer 
        validateDealer(dealer);
        
        // if everything is ok proceed 
        return dealerMapper.mapToDealerDTO(
                dealerRepository.save(dealerMapper.mapToDealerEntity(
                        dealer)));
    }

    /**
     * Updates an existing dealer.
     *
     * @param id            The ID of the dealer to update.
     * @param updatedDealer The updated dealer entity as DTO.
     * @return The updated dealer entity as DTO.
     * @throws DealerNotFoundException if the dealer is not found.
     */
    @Override
    public DealerDTO updateDealer(UUID id, DealerDTO updatedDealer) {
        logger.info("Updating dealer with ID: {}, Updated dealer: {}", id, updatedDealer);
        DealerDTO existingDealer = getDealerById(id);
        // Update the fields of existingDealer with the fields from updatedDealer
        // Check updatedDealer 
        validateDealer(updatedDealer);
        updatedDealer.setId(id);
        // if everything is OK update DealerEntity else throw an exception
        return dealerMapper.mapToDealerDTO(
                dealerRepository.save(
                        dealerMapper.mapToDealerEntity(updatedDealer)));
    }

    /**
     * Deletes a dealer by its ID.
     *
     * @param id The ID of the dealer to delete.
     * @throws DealerNotFoundException if the dealer is not found.
     */
    @Override
    public void deleteDealer(UUID id) {
        logger.info("Deleting dealer with ID: {}", id);
        DealerDTO existingDealer = getDealerById(id);
        dealerRepository.delete(dealerMapper.mapToDealerEntity(existingDealer));
    }
    
    
    /**
     * Validate and check the dealerDTO Object  .
     *
     * @param dealer The dealer Object to be Validated.
     * @return void if everything is ok else 
     * @throws ValidationException if there is an issue during validation.
     */
    private void validateDealer(DealerDTO dealer) {
        
        // Check if the tier limit  > 0 or throw an exception
        if (dealer.getTierLimit() <= 0) {
            throw new ValidationException("Dealer TierLimit must be greater than 0.");
        }
        // Check if the Dealer name is empty or null
        if (dealer.getName() == null || dealer.getName().isEmpty()) {
            throw new ValidationException("Dealer name is required.");
        }
        

    }

}
