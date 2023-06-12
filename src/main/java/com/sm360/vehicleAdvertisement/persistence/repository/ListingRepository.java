/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sm360.vehicleAdvertisement.persistence.repository;

import com.sm360.vehicleAdvertisement.persistence.entity.ListingEntity;
import com.sm360.vehicleAdvertisement.persistence.entity.ListingState;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * Repository interface for accessing and manipulating Listing entities in the database.
 * It extends Spring Data JPA's {@link JpaRepository} interface, providing
 * CRUD (Create, Read, Update, Delete) operations for ListingEntity.
 * 
 * @author fabrice
 * 
 */
public interface ListingRepository extends JpaRepository<ListingEntity, UUID> {
    
    List<ListingEntity> findByDealerIdAndState(UUID dealerId, ListingState state);
    
    
    /**
     * Checks if the tier limit is exceeded for the specified dealer.
     *
     * @param dealerId The ID of the dealer.
     * @return {@code true} if the tier limit is exceeded, {@code false} otherwise.
     */
    @Query(ListingQueryConstants.COUNT_LISTINGS_BY_DEALER)
    boolean isTierLimitExceeded(@Param("dealerId") UUID dealerId);
    
    /**
     * Find the oldest published listing for a specific dealer.
     *
     * @param dealerId The ID of the dealer.
     * @return An Optional containing the oldest published listing, or an empty Optional if no listing is found.
     */
    @Query(ListingQueryConstants.RETRIEVE_OLDEST_PUBLISHED_LISTING)
    Optional<ListingEntity> findOldestPublishedListingByDealerId(@Param("dealerId") UUID dealerId);

    
    
}