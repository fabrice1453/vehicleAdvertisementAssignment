/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.dto;

/**
 * Enum representing the available options for dealing with the situation when the tier limit is reached when publishing a listing.
 * The handling option for dealing with the situation when the tier limit is reached.
 * Available options:
 * - ERROR: Return an error to the client when the tier limit is reached.
 * - PUBLISH_AND_UNPUBLISH : Publish a listing, but unpublish the oldest listing of a dealer to conform to the tier limit.
 * 
 * @author fabrice
 * 
 */
public enum TierLimitHandling {
    /** 
     * Option to return an error when the tier limit is reached
     */
    ERROR,                    
    
    /** 
     * Option to publish a listing and unpublish the oldest listing to conform to the tier limit 
     */
    PUBLISH_AND_UNPUBLISH   
    
}