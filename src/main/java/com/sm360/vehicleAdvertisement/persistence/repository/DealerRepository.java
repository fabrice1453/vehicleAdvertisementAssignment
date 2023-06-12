/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sm360.vehicleAdvertisement.persistence.repository;

import com.sm360.vehicleAdvertisement.persistence.entity.DealerEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * Repository interface for accessing and manipulating Dealer entities in the database.
 * It extends Spring Data JPA's {@link JpaRepository} interface, providing
 * CRUD (Create, Read, Update, Delete) operations for DealerEntity.
 * 
 * @author fabrice
 * 
 */
public interface DealerRepository extends JpaRepository<DealerEntity, UUID> {
    
}
