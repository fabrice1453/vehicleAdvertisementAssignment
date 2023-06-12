/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sm360.vehicleAdvertisement.exception.InvalidListingStateException;
import com.sm360.vehicleAdvertisement.persistence.entity.ListingState;

import java.io.IOException;

/**
 * 
 * The ListingStateDeserializer class is a custom deserializer for converting a JSON string representation
 * of a ListingState to its corresponding ListingState enum value.
 * 
 * @author fabrice
 * 
 */
public class ListingStateDeserializer extends JsonDeserializer<ListingState> {

    /**
     * Deserialize a JSON string into a ListingState enum value.
     *
     * @param jsonParser             The JSON parser.
     * @param deserializationContext The deserialization context.
     * @return The ListingState enum value.
     * @throws IOException If an error occurs during deserialization.
     */
    @Override
    public ListingState deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String value = jsonParser.getText();

        if ("draft".equalsIgnoreCase(value)) {
            return ListingState.DRAFT;
        } else if ("published".equalsIgnoreCase(value)) {
            return ListingState.PUBLISHED;
        }

        throw new InvalidListingStateException("Invalid ListingState value: " + value);
    }
}

