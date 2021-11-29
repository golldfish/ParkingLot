package com.patronage.parkinglot.model.DTO;

import com.patronage.parkinglot.model.ParkingPlace;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.patronage.parkinglot.service.mapper.Mapper.convertParkingPlaceToDTO;
import static com.patronage.parkinglot.service.mapper.Mapper.convertToParkingPlaceEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingPlaceDTOTest {

    @Test
    @DisplayName("Convert ParkingPlace Entity to ParkingPlaceDTO -> correct")
    public void whenConvertParkingPlaceToParkingPlaceDTO_thenCorrect() {
        //given
        ParkingPlace place = new ParkingPlace();
        place.setId(1L);
        place.setPlaceNumber(1);
        place.setTier(1);
        place.setPlaceForDisabledPeople(true);
        //when
        ParkingPlaceDTO parkingPlaceDTO = convertParkingPlaceToDTO(place);
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
        ParkingPlaceDTO placeDTO = new ParkingPlaceDTO();
        placeDTO.setId(1L);
        placeDTO.setPlaceNumber(1);
        placeDTO.setTier(1);
        placeDTO.setPlaceForDisabledPeople(false);
        //when
        ParkingPlace place = convertToParkingPlaceEntity(placeDTO);
        //then
        assertEquals(placeDTO.getId(), place.getId());
        assertEquals(placeDTO.getPlaceNumber(), place.getPlaceNumber());
        assertEquals(placeDTO.getTier(), place.getTier());
        assertEquals(placeDTO.isPlaceForDisabledPeople(), place.isPlaceForDisabledPeople());
    }

}