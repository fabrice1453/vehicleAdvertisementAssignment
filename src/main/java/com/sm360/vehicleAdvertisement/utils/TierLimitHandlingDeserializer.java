/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.utils;

/**
 *
 * @author fabrice
 */
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sm360.vehicleAdvertisement.dto.TierLimitHandling;
import com.sm360.vehicleAdvertisement.exception.UnknownTierLimitHandlingException;

import java.io.IOException;

public class TierLimitHandlingDeserializer extends JsonDeserializer<TierLimitHandling> {

    /**
     * Deserializes the JSON value into a TierLimitHandling enum value.
     *
     * @param jsonParser           The JsonParser object.
     * @param deserializationContext The DeserializationContext object.
     * @return The deserialized TierLimitHandling enum value.
     * @throws IOException If an I/O error occurs during deserialization.
     */
    @Override
    public TierLimitHandling deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String value = jsonParser.getText();

        if ("error".equalsIgnoreCase(value)) {
            return TierLimitHandling.ERROR;
        } else if ("publish_and_unpublish".equalsIgnoreCase(value)) {
            return TierLimitHandling.PUBLISH_AND_UNPUBLISH;
        }

        throw new UnknownTierLimitHandlingException("Invalid TierLimitHandling value: " + value);
    }
}
