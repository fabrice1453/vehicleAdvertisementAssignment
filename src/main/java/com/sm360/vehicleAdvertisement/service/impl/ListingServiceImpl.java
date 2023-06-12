/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.service.impl;

import com.sm360.vehicleAdvertisement.dto.DealerDTO;
import com.sm360.vehicleAdvertisement.dto.DealerMapper;
import com.sm360.vehicleAdvertisement.dto.ListingDTO;
import com.sm360.vehicleAdvertisement.dto.ListingMapper;
import com.sm360.vehicleAdvertisement.dto.ListingState;
import com.sm360.vehicleAdvertisement.dto.TierLimitHandling;
import com.sm360.vehicleAdvertisement.exception.DealerNotFoundException;
import com.sm360.vehicleAdvertisement.exception.InvalidListingStateException;
import com.sm360.vehicleAdvertisement.exception.ListingNotFoundException;
import com.sm360.vehicleAdvertisement.exception.ValidationException;
import com.sm360.vehicleAdvertisement.exception.TierLimitExceededException;
import com.sm360.vehicleAdvertisement.exception.UnauthorizedAccessException;
import com.sm360.vehicleAdvertisement.exception.UnknownTierLimitHandlingException;
import com.sm360.vehicleAdvertisement.persistence.entity.DealerEntity;
import com.sm360.vehicleAdvertisement.persistence.entity.ListingEntity;
import com.sm360.vehicleAdvertisement.persistence.repository.DealerRepository;
import com.sm360.vehicleAdvertisement.persistence.repository.ListingRepository;
import com.sm360.vehicleAdvertisement.service.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * Implementation of the ListingService interface that provides operations related to listings.
 * 
 * @author fabrice
 * 
 */



@Service
@Slf4j
public class ListingServiceImpl implements ListingService{
    
    private static final Logger logger = LoggerFactory.getLogger(ListingServiceImpl.class);
    
    /** {@link ListingRepository} bean */
    @Autowired
    private ListingRepository listingRepository;
    
    /** {@link DealerRepository} bean */
    @Autowired
    private DealerRepository dealerRepository;
    
    /** Mapper for {@link ListingEntity} class */
    @Autowired
    private ListingMapper listingMapper;
    
    /** Mapper for {@link DealerEntity} class */
    @Autowired
    private DealerMapper dealerMapper;
    
    /**
     * Create a new listing.
     *
     * @param listing The listing to create.
     * @return The created listing.
     */
    @Transactional
    @Override
    public ListingDTO createListing(ListingDTO listing) {
        // Log the creation action
        logger.info("Creating listing: {}", listing);

        // Validate the listing data
        validateListing(listing);
        
        // Verify the dealer Id exist 
        
        getDealerById(listing.getDealerId());

        // Set the initial state of the listing
        listing.setState(listingMapper.mapListingStateDTOTomapListingStateEntity(ListingState.DRAFT));

        // Set the creation timestamp
        listing.setCreatedAt(LocalDateTime.now());

        // Save the newly created listing
        ListingEntity createdListing = listingRepository.save(listingMapper.mapToListingEntity(listing));

        // Log the created listing
        logger.info("Listing created: {}", createdListing);

        // Convert and return the created listing as a DTO
        return listingMapper.mapToListingDTO(createdListing);
    }

    /**
     * Update an existing listing.
     *
     * @param listing The updated listing.
     * @return The updated listing.
     * @throws ListingNotFoundException if the listing with the specified ID is not found.
     */
    @Transactional
    @Override 
    public ListingDTO updateListing(ListingDTO listing) {
        // Log the update action
        logger.info("Updating listing: {}", listing);

        // Validate the listing data
        validateListing(listing);
        
        // Get the existing listing by ID
        ListingDTO existingListing = getListingById(listing.getId());
        
        // Verify the dealer Id exist 
        getDealerById(listing.getDealerId());
        
        // Verify the dealer is the owner of the Listing else throw an UnauthorizedAccessException 
        if(!listing.getDealerId().equals(existingListing.getDealerId())){
            
            throw new UnauthorizedAccessException("This Listing does not belong to the specified dealer.");

        }
                
        // Update the vehicle details
        existingListing.setVehicle(listing.getVehicle());

        // Update the price
        existingListing.setPrice(listing.getPrice());

        // Save the updated listing
        ListingEntity updatedListing = listingRepository.save(listingMapper.mapToListingEntity(existingListing));

        // Log the updated listing
        logger.info("Listing updated: {}", updatedListing);

        // Convert and return the updated listing as a DTO
        return listingMapper.mapToListingDTO(updatedListing);
    }


    /**
     * Get a list of listings based on dealer ID and state.
     *
     * @param dealerId The dealer ID.
     * @param state The listing state.
     * @return The list of listings.
     * @throws DealerNotFoundException if the dealer with the specified ID is not found.
     */
    @Transactional
    @Override 
   public List<ListingDTO> getListingsByDealerAndState(UUID dealerId, ListingState state) {
        logger.info("Fetching listings by dealer ID {} and state {}", dealerId, state);

        // Get the dealer, throwing an exception if not found
        dealerRepository.findById(dealerId)
                .map(dealerMapper::mapToDealerDTO)
                .orElseThrow(() -> new DealerNotFoundException("Dealer not found with ID: " + dealerId));

        // Fetch the listings from the repository
        List<ListingEntity> listings = listingRepository.findByDealerIdAndState(
                dealerId,
                listingMapper.mapListingStateDTOTomapListingStateEntity(state)
        );

        logger.info("Listings fetched: {}", listings);

        // Map the ListingEntity objects to ListingDTO objects
        return listingMapper.mapToListingDTO(listings);
   }


    /**
     * Publish a listing.
     *
     * @param listingId The ID of the listing to publish.
     * @param tierLimitHandlingValue  The handling option for dealing with the situation when the tier limit is reached.
     * @return The published listing.
     * @throws ListingNotFoundException if the listing with the specified ID is not found.
     */
    @Transactional
    @Override 
    public ListingDTO publishListing(UUID listingId, TierLimitHandling tierLimitHandlingValue) {
        logger.info("Publishing listing with ID: {} and tierLimitHandlingValue {}", listingId, tierLimitHandlingValue);
        
        // Check if tier limit handling is known
        if (!tierLimitHandlingValue.equals(TierLimitHandling.ERROR)
                && !tierLimitHandlingValue.equals(TierLimitHandling.PUBLISH_AND_UNPUBLISH)) {
            throw new UnknownTierLimitHandlingException("Unknown tier limit handling value: " + tierLimitHandlingValue);
        }

        // Retrieve the ListingEntity from the repository by ID
        Optional<ListingEntity> optionalListingEntity = listingRepository.findById(listingId);

        // Throw an exception if the ListingEntity is not found
        ListingEntity listingEntity = optionalListingEntity.orElseThrow(() -> new ListingNotFoundException("Listing not found with ID: " + listingId));
        
         // verify the crurrent state of the listing if it is already published throw and exception
        if(listingEntity.getState().toString().equalsIgnoreCase(ListingState.PUBLISHED.toString())){
             
            throw new InvalidListingStateException("This Listing is already published.");
        }
        
        // Check the number of published items by The dealer and verify if the tier limit is exceeded 
        if( listingRepository.isTierLimitExceeded(listingEntity.getDealerId())){
            
            logger.info("Tier limit is exceeded for dealer_id : {} , tierLimitHandlingValue : {}", listingEntity.getDealerId(), tierLimitHandlingValue);
            
        // if the dealer's TierLimitHandling is   ERROR   throw TierLimitExceededException else look for the oldest listing to be unpublish
            if(tierLimitHandlingValue.equals(TierLimitHandling.ERROR)){
                
                throw new TierLimitExceededException("Tier limit exceeded: " + tierLimitHandlingValue);
                
            }else {
            
                Optional<ListingEntity> optionalListing = listingRepository.findOldestPublishedListingByDealerId(listingEntity.getDealerId());

                if (optionalListing.isPresent()) {
                    
                    logger.info("unpublish The oldest Listing published  listing_id  : {}", optionalListing.get().getId());
                    
                    unpublishListing( optionalListing.get().getId());
                }
            
            }
        
        }

        // Update the ListingEntity with the desired state
        listingEntity.setState(listingMapper.mapListingStateDTOTomapListingStateEntity(ListingState.PUBLISHED));

        // Save the updated ListingEntity to persist the changes
        ListingEntity publishedListingEntity = listingRepository.save(listingEntity);

        logger.info("Listing published: {}", publishedListingEntity);

        // Map the published ListingEntity to ListingDTO
        return listingMapper.mapToListingDTO(publishedListingEntity);
    }


    /**
      * Unpublishes a listing with the specified ID.
      *
      * @param listingId The ID of the listing to unpublish.
      * @return The unpublished listing.
      * @throws ListingNotFoundException if the listing with the specified ID is not found.
      */
    @Override
    public ListingDTO unpublishListing(UUID listingId) {
         logger.info("Unpublishing listing with ID: {}", listingId);

         // Retrieve the listing by ID
         ListingDTO listing = getListingById(listingId);
         
         // verify the crurrent state of the listing if it is already draft throw and exception
         if(listing.getState().toString().equalsIgnoreCase(ListingState.DRAFT.toString())){
             
             throw new InvalidListingStateException("This Listing is already unpublished.");
         }
         
         // Set the listing state to DRAFT
         listing.setState(listingMapper.mapListingStateDTOTomapListingStateEntity(ListingState.DRAFT));

         // Save the updated listing entity
         ListingEntity unpublishedListing = listingRepository.save(listingMapper.mapToListingEntity(listing));

         logger.info("Listing unpublished: {}", unpublishedListing);

         // Map the unpublished listing entity to a DTO and return it
         return listingMapper.mapToListingDTO(unpublishedListing);
    }

    /**
      * Retrieves a listing with the specified ID.
      *
      * @param listingId The ID of the listing to retrieve.
      * @return The listing with the specified ID.
      * @throws ListingNotFoundException if the listing with the specified ID is not found.
      */
    private ListingDTO getListingById(UUID listingId) {
         // Find the listing entity by ID
         Optional<ListingEntity> optionalListing = listingRepository.findById(listingId);

         if (optionalListing.isPresent()) {
             // Map the listing entity to a DTO and return it
             return listingMapper.mapToListingDTO(optionalListing.get());
         } else {
             // Throw an exception if the listing is not found
             throw new ListingNotFoundException("Listing not found with ID: " + listingId);
         }
     }


    private DealerDTO getDealerById(UUID dealerId) {
        Optional<DealerEntity> optionalDealer = dealerRepository.findById(dealerId);
        if (optionalDealer.isPresent()) {
            return dealerMapper.mapToDealerDTO(optionalDealer.get());
        } else {
            throw new DealerNotFoundException("Dealer not found with ID: " + dealerId);
        }
    }

    private void validateListing(ListingDTO listing) {
        if (listing.getPrice() <= 0) {
            throw new ValidationException("Listing price must be greater than 0.");
        }

        // Example: Check if the vehicle name is empty or null
        if (listing.getVehicle() == null || listing.getVehicle().isEmpty()) {
            throw new ValidationException("Vehicle name is required.");
        }

        // Example: Check if the createdAt date is in the past
        if (listing.getCreatedAt() != null && listing.getCreatedAt().isAfter(LocalDateTime.now())) {
            throw new ValidationException("Invalid createdAt date.");
        }

//        // Example: Check if the state is valid
//        if (listing.getState() == null || (!listing.getState().equals(ListingState.PUBLISHED) && !listing.getState().equals(ListingState.DRAFT))) {
//            throw new ValidationException("Invalid listing state.");
//        }

    }

}
