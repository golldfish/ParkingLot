package com.patronage.parkinglot.model.dto;

import com.patronage.parkinglot.dto.ParkingPlaceDto;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.service.mapper.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingPlaceDtoTest {
    //private final MapStructMapper mapper = new MapStructMapper();
    private final Map mapper = Mappers.getMapper(Map.class);

    @Test
    @DisplayName("Convert ParkingPlace Entity to ParkingPlaceDTO -> correct")
    public void whenConvertParkingPlaceToParkingPlaceDTO_thenCorrect() {
        //given
        final ParkingPlace place = new ParkingPlace();
        place.setId(1L);
        place.setPlaceNumber(1);
        place.setTier(1);
        place.setPlaceForDisabledPeople(true);
        //when
        final ParkingPlaceDto parkingPlaceDTO = mapper.convertParkingPlaceToDto(place);
        //then
        assertEquals(place.getId(), parkingPlaceDTO.getId());
        assertEquals(place.getPlaceNumber(), parkingPlaceDTO.getPlaceNumber());
        assertEquals(place.getTier(), parkingPlaceDTO.getTier());
        assertEquals(place.isPlaceForDisabledPeople(), parkingPlaceDTO.isPlaceForDisabledPeople());

    }

    @Test
    @DisplayName("Convert ParkingPlaceDTO to ParkingPlace Entity -> correct")
    public void whenConvertParkingPlaceDTOToParkingPlaceEntity_thenCorrect() {
        //given
        final ParkingPlaceDto placeDTO = new ParkingPlaceDto(1L, 1, 1, false);
        //when
        final ParkingPlace place = mapper.convertToParkingPlaceEntity(placeDTO);
        //then
        assertEquals(placeDTO.getId(), place.getId());
        assertEquals(placeDTO.getPlaceNumber(), place.getPlaceNumber());
        assertEquals(placeDTO.getTier(), place.getTier());
        assertEquals(placeDTO.isPlaceForDisabledPeople(), place.isPlaceForDisabledPeople());
    }

}