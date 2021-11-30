package com.patronage.parkinglot.service;

import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.repository.ParkingPlaceRepository;
import com.patronage.parkinglot.response.exception.AlreadyExistsException;
import com.patronage.parkinglot.response.exception.NotFoundException;
import com.patronage.parkinglot.service.mapper.MapStructMapperImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
public class ParkingPlaceServiceImpl implements ParkingPlaceService {
    private final ParkingPlaceRepository parkingPlaceRepository;
    private final MapStructMapperImpl mapper = new MapStructMapperImpl();

    @Override
    public List<ParkingPlaceDTO> getPlaces() {
        final List<ParkingPlace> parkingPlaces = parkingPlaceRepository.findAll();
        final List<ParkingPlaceDTO> placeDTOS = new ArrayList<>();
        parkingPlaces.forEach(place -> {
            placeDTOS.add(mapper.convertParkingPlaceToDTO(place));
        });
        return placeDTOS;
    }

    @Override
    public void createNewPlace(final ParkingPlaceDTO parkingPlaceDTO) throws AlreadyExistsException {
        if (placeExists(parkingPlaceDTO.getPlaceNumber(), parkingPlaceDTO.getTier())) {
            throw new AlreadyExistsException("Place number: " + parkingPlaceDTO.getPlaceNumber() + " on tier: " + parkingPlaceDTO.getTier() + " already exists.");
        }
        parkingPlaceRepository.save(mapper.convertToParkingPlaceEntity(parkingPlaceDTO));
    }


    @Override
    public ParkingPlaceDTO getPlaceByPlaceNumberAndTier(final int tier, final int placeNumber) throws NotFoundException {
        final ParkingPlace place = parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(placeNumber, tier).orElseThrow(() -> new NotFoundException("Place number: " + placeNumber + " on tier: " + tier + " is not found."));
        return mapper.convertParkingPlaceToDTO(place);
    }

    @Override
    public void deletePlaceById(final Long id) throws NotFoundException {
        if (!placeExists(id)) {
            throw new NotFoundException("Place with id: " + id + " is not found.");
        }
        parkingPlaceRepository.deleteById(id);
    }

    @Override
    public List<ParkingPlaceDTO> getAllFreePlaces() {
        final List<ParkingPlace> parkingPlaces = parkingPlaceRepository.findAll();
        final List<ParkingPlace> freePlaces = parkingPlaces.stream()
                .filter(parkingPlace -> parkingPlace.getReservation() == null)
                .collect(Collectors.toList());

        final List<ParkingPlaceDTO> placeDTOS = new ArrayList<>();
        freePlaces.forEach(place -> {
            placeDTOS.add(mapper.convertParkingPlaceToDTO(place));
        });
        System.out.println(placeDTOS);
        return placeDTOS;
    }


    private boolean placeExists(final int placeNumber, final int tier) {
        return parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(placeNumber, tier).isPresent();
    }

    private boolean placeExists(final Long id) {
        return parkingPlaceRepository.findById(id).isPresent();
    }

}
