package com.sm360.vehicleAdvertisement.dto;

import com.sm360.vehicleAdvertisement.persistence.entity.DealerEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DealerMapper {

    private final ModelMapper modelMapper;

    public DealerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Maps a DealerEntity to a DealerDTO using ModelMapper.
     *
     * @param entity The DealerEntity to be mapped.
     * @return The mapped DealerDTO.
     */

    public DealerDTO mapToDealerDTO(DealerEntity entity) {
        return modelMapper.map(entity, DealerDTO.class);
    }

    /**
     * Maps a DealerDTO to a DealerEntity using ModelMapper.
     *
     * @param dto The DealerDTO to be mapped.
     * @return The mapped DealerEntity.
     */
    public DealerEntity mapToDealerEntity(DealerDTO dto) {
        return modelMapper.map(dto, DealerEntity.class);
    }

    /**
     * Maps a list of DealerEntity to a list of DealerDTO using ModelMapper.
     *
     * @param entity The list of DealerEntity to be mapped.
     * @return The mapped list of DealerDTO.
     */
    public List<DealerDTO> mapToDealerDTO(List<DealerEntity> entity) {
        return entity.stream()
                .map(this::mapToDealerDTO)
                .collect(Collectors.toList());
    }
}
