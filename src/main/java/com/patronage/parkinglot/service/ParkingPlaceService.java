package com.patronage.parkinglot.service;

import com.patronage.parkinglot.dto.ParkingPlaceDto;
import com.patronage.parkinglot.exception.AlreadyExistsException;
import com.patronage.parkinglot.exception.NotFoundException;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.repository.ParkingPlaceRepository;
import com.patronage.parkinglot.repository.ReservationRepository;
import com.patronage.parkinglot.service.mapper.Map;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
public class ParkingPlaceService {
    private final ParkingPlaceRepository parkingPlaceRepository;
    private final ReservationRepository reservationRepository;
    private final Map mapper = Mappers.getMapper(Map.class);

    public List<ParkingPlaceDto> getPlaces() {
        final List<ParkingPlace> parkingPlaces = parkingPlaceRepository.findAll();
        final List<ParkingPlaceDto> parkingPlaceDtos = new ArrayList<>();
        parkingPlaces.forEach(place -> {
            parkingPlaceDtos.add(mapper.convertParkingPlaceToDto(place));
        });
        return parkingPlaceDtos;
    }


    public void createNewPlace(final int placeNumber, final int tier, final boolean isDisabled) throws AlreadyExistsException {
        final ParkingPlaceDto parkingPlaceDto = ParkingPlaceDto.builder().placeNumber(placeNumber).tier(tier).placeForDisabledPeople(isDisabled).build();
        parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(placeNumber, tier)
                .ifPresentOrElse(place -> {
                    throw new AlreadyExistsException(String.format("Place number: %s on tier: %s already exists.", placeNumber, tier));
                }, () -> parkingPlaceRepository.save(mapper.convertToParkingPlaceEntity(parkingPlaceDto)));
    }


    public ParkingPlaceDto getPlaceByPlaceNumberAndTier(final int tier, final int placeNumber) throws NotFoundException {
        final ParkingPlace place = parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(placeNumber, tier).orElseThrow(() -> new NotFoundException("Place number: " + placeNumber + " on tier: " + tier + " is not found."));
        return mapper.convertParkingPlaceToDto(place);
    }


    public void deletePlaceById(final Long id) throws NotFoundException {
        reservationRepository.findReservationByParkingPlace_Id(id).ifPresent(place -> {
            throw new AlreadyExistsException(String.format("Unable to remove because reservation for place with id: %s already exists.", id));
        });
        parkingPlaceRepository.findParkingPlaceById(id).ifPresentOrElse(place -> {
            parkingPlaceRepository.deleteById(id);
        }, () -> {
            throw new NotFoundException(String.format("Place with id: %s is not found.", id));
        });

    }


    public List<ParkingPlaceDto> getAllFreePlaces() {
        final List<ParkingPlace> parkingPlaces = parkingPlaceRepository.findAll();
        final List<ParkingPlace> freePlaces = parkingPlaces.stream()
                .filter(parkingPlace -> parkingPlace.getReservation() == null)
                .collect(Collectors.toList());

        final List<ParkingPlaceDto> placeDtos = new ArrayList<>();
        freePlaces.forEach(place -> {
            placeDtos.add(mapper.convertParkingPlaceToDto(place));
        });
        return placeDtos;
    }
}

