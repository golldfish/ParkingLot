package com.patronage.parkinglot.service;

import com.patronage.parkinglot.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.exception.AlreadyExistsException;
import com.patronage.parkinglot.exception.NotFoundException;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.repository.ParkingPlaceRepository;
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
    //private final MapStructMapper mapper = new MapStructMapper();
    private final Map mapper = Mappers.getMapper(Map.class);

    public List<ParkingPlaceDTO> getPlaces() {
        final List<ParkingPlace> parkingPlaces = parkingPlaceRepository.findAll();
        final List<ParkingPlaceDTO> placeDTOS = new ArrayList<>();
        parkingPlaces.forEach(place -> {
            placeDTOS.add(mapper.convertParkingPlaceToDTO(place));
        });
        return placeDTOS;
    }


    public void createNewPlace(final ParkingPlaceDTO parkingPlaceDTO) throws AlreadyExistsException {
        if (placeExists(parkingPlaceDTO.getPlaceNumber(), parkingPlaceDTO.getTier())) {
            throw new AlreadyExistsException("Place number: " + parkingPlaceDTO.getPlaceNumber() + " on tier: " + parkingPlaceDTO.getTier() + " already exists.");
        }
        parkingPlaceRepository.save(mapper.convertToParkingPlaceEntity(parkingPlaceDTO));
    }


    public ParkingPlaceDTO getPlaceByPlaceNumberAndTier(final int tier, final int placeNumber) throws NotFoundException {
        final ParkingPlace place = parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(placeNumber, tier).orElseThrow(() -> new NotFoundException("Place number: " + placeNumber + " on tier: " + tier + " is not found."));
        return mapper.convertParkingPlaceToDTO(place);
    }


    public void deletePlaceById(final Long id) throws NotFoundException {
        if (!placeExists(id)) {
            throw new NotFoundException("Place with id: " + id + " is not found.");
        }
        parkingPlaceRepository.deleteById(id);
    }


    public List<ParkingPlaceDTO> getAllFreePlaces() {
        final List<ParkingPlace> parkingPlaces = parkingPlaceRepository.findAll();
        final List<ParkingPlace> freePlaces = parkingPlaces.stream().filter(parkingPlace -> parkingPlace.getReservation() == null).collect(Collectors.toList());

        final List<ParkingPlaceDTO> placeDTOS = new ArrayList<>();
        freePlaces.forEach(place -> {
            placeDTOS.add(mapper.convertParkingPlaceToDTO(place));
        });
        return placeDTOS;
    }


    private boolean placeExists(final int placeNumber, final int tier) {
        return parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(placeNumber, tier).isPresent();
    }

    private boolean placeExists(final Long id) {
        return parkingPlaceRepository.findById(id).isPresent();
    }

}

