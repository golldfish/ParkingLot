package com.patronage.parkinglot.service;

import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.repository.ParkingPlaceRepository;
import com.patronage.parkinglot.repository.ReservationRepository;
import com.patronage.parkinglot.response.exception.AlreadyExistsException;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class ParkingPlaceServiceTest {

    @Mock
    ParkingPlaceRepository parkingPlaceRepository;
    @Mock
    ReservationRepository reservationRepository;
    @InjectMocks
    ParkingPlaceServiceImpl placeService;

    @Rule
    public ExpectedException exceptionRule;

    @ExtendWith(MockitoExtension.class)
    @Test
    @DisplayName("Get list of places")
    public void getPlacesList() {
        //given
        final ParkingPlace place = new ParkingPlace();
        place.setPlaceNumber(1);
        place.setTier(1);
        place.setPlaceForDisabledPeople(true);
        place.setReserved(false);
        final List<ParkingPlace> parkingPlaceList = List.of(place);
        //when
        when(parkingPlaceRepository.findAll()).thenReturn(parkingPlaceList);
        final List<ParkingPlaceDTO> parkingPlaceDto = placeService.getPlaces();
        //then
        assertEquals(parkingPlaceDto.get(0).getPlaceNumber(), parkingPlaceList.get(0).getPlaceNumber());
    }

    @ExtendWith(MockitoExtension.class)
    @Test
    @DisplayName("Create new place using existing one -> should return AlreadyExistsException")
    public void tryToCreateNewPlaceWithExistingOne_thenReturnAlreadyExistException() {
        //given
        final ParkingPlaceDTO placeDTO = new ParkingPlaceDTO(1L, 1, 1, false, false);
        final String expectedMessage = "Place number: " + placeDTO.getPlaceNumber() + " on tier: " + placeDTO.getTier() + " already exists.";
        //when
        final Exception exception = assertThrows(AlreadyExistsException.class, () -> {
            when(parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(placeDTO.getPlaceNumber(), placeDTO.getTier())).thenReturn(Optional.of(new ParkingPlace()));
            placeService.createNewPlace(placeDTO);
        });
        //then
        final String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ExtendWith(MockitoExtension.class)
    @Test
    @DisplayName("Create new place")
    public void tryToCreateNewPlace() throws Exception {
        //given
        final ParkingPlaceDTO placeDTO = new ParkingPlaceDTO(1L, 1, 1, false, false);
        //when
        when(parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(placeDTO.getPlaceNumber(), placeDTO.getTier())).thenReturn(Optional.empty());
        when(parkingPlaceRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
        //then
        placeService.createNewPlace(placeDTO);
    }

    @ExtendWith(MockitoExtension.class)
    @Test
    @DisplayName("Get place by number and tier")
    public void tryToGetPlaceByNumberAndTier_thenReturnPlace() throws Exception {
        //given
        final ParkingPlace place = new ParkingPlace();
        place.setPlaceNumber(1);
        place.setTier(1);
        place.setPlaceForDisabledPeople(true);
        place.setReserved(false);
        //when
        when(parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(place.getPlaceNumber(), place.getTier())).thenReturn(Optional.of(place));
        final ParkingPlaceDTO parkingPlaceDto = placeService.getPlaceByPlaceNumberAndTier(place.getPlaceNumber(), place.getTier());
        //then
        assertEquals(parkingPlaceDto.getPlaceNumber(), place.getPlaceNumber());
        assertEquals(parkingPlaceDto.getTier(), place.getTier());
    }
}