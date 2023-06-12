package com.sm360.vehicleAdvertisement.dto;

import com.sm360.vehicleAdvertisement.exception.InvalidListingStateException;
import com.sm360.vehicleAdvertisement.exception.UnknownTierLimitHandlingException;
import com.sm360.vehicleAdvertisement.persistence.entity.ListingEntity;
import com.sm360.vehicleAdvertisement.persistence.entity.ListingState;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

@Component
public class ListingMapper {

    private final ModelMapper modelMapper;

    public ListingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Maps a ListingEntity to a ListingDTO.
     *
     * @param entity The ListingEntity to be mapped.
     * @return The mapped ListingDTO.
     */
    public ListingDTO mapToListingDTO(ListingEntity entity) {
        return modelMapper.map(entity, ListingDTO.class);
    }

    /**
     * Maps a ListingDTO to a ListingEntity.
     *
     * @param dto The ListingDTO to be mapped.
     * @return The mapped ListingEntity.
     */
    public ListingEntity mapToListingEntity(ListingDTO dto) {
        return modelMapper.map(dto, ListingEntity.class);
    }

    /**
     * Maps a list of ListingEntity to a list of ListingDTO.
     *
     * @param entity The list of ListingEntity to be mapped.
     * @return The mapped list of ListingDTO.
     */
    public List<ListingDTO> mapToListingDTO(List<ListingEntity> entity) {
        return entity.stream()
                .map(this::mapToListingDTO)
                .collect(Collectors.toList());
    }

    /**
     * Maps a ListingState enum value to a ListingState enum value.
     *
     * @param state The ListingState enum value to be mapped.
     * @return The mapped ListingState value.
     */
    public ListingState mapListingStateEntityTomapListingStateDTO(ListingState state) {
        return modelMapper.map(state, ListingState.class);
    }

    /**
     * Maps a ListingState enum value to a ListingState enum value.
     *
     * @param state The ListingState enum value to be mapped.
     * @return The mapped ListingState value.
     */
    public com.sm360.vehicleAdvertisement.persistence.entity.ListingState  mapListingStateDTOTomapListingStateEntity(com.sm360.vehicleAdvertisement.dto.ListingState  state) {
        return modelMapper.map(state, ListingState.class);
    }
    
    
    /**
     * Configures  a custom converter for converting a String to ListingState enum.
     * @param source the string value to be converted 
     * @return the ListingState to be returned
     */
    public com.sm360.vehicleAdvertisement.dto.ListingState  convertStringToListingState(String source) {
         
        if ("draft".equalsIgnoreCase(source)) {
                return com.sm360.vehicleAdvertisement.dto.ListingState.DRAFT;
            } else if ("published".equalsIgnoreCase(source)) {
                return com.sm360.vehicleAdvertisement.dto.ListingState.PUBLISHED;
            }
            throw new InvalidListingStateException("Invalid ListingState value: " + source);
    }
    
    /**
     * Configures  a custom converter for converting a String to TierLimitHandling enum.
     * @param source the string value to be converted 
     * @return the ListingState to be returned
     */
    public TierLimitHandling convertStringToTierLimitHandling(String source) {
         
        if ("PUBLISH_AND_UNPUBLISH".equalsIgnoreCase(source)) {
                return TierLimitHandling.PUBLISH_AND_UNPUBLISH;
            } else if ("ERROR".equalsIgnoreCase(source)) {
                return TierLimitHandling.ERROR;
            }
            throw new UnknownTierLimitHandlingException("Invalid TierLimit Handling value: " + source + " are only authorized PUBLISH_AND_UNPUBLISH or ERROR");
    }

}
