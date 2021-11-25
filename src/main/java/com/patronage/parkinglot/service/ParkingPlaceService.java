package com.patronage.parkinglot.service;

import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.repository.ParkingPlaceRepository;
import com.patronage.parkinglot.repository.ReservationRepository;
import com.patronage.parkinglot.response.exception.AlreadyExistsException;
import com.patronage.parkinglot.response.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ParkingPlaceService implements IParkingPlaceService {
    private final ParkingPlaceRepository parkingPlaceRepository;
    private final ReservationRepository reservationRepository;
    private final ModelMapper mapper;

    @Override
    public List<ParkingPlaceDTO> getPlaces() {
        List<ParkingPlace> parkingPlaces = parkingPlaceRepository.findAll();
        List<ParkingPlaceDTO> placeDTOS = new ArrayList<>();
        parkingPlaces.forEach(place -> {
            placeDTOS.add(convertParkingPlaceToDto(place));
        });
        return placeDTOS;
    }

    @Override
    public void createNewPlace(ParkingPlaceDTO parkingPlaceDTO) throws AlreadyExistsException {
        if (placeExists(parkingPlaceDTO.getPlaceNumber(), parkingPlaceDTO.getTier())) {
            throw new AlreadyExistsException("Place number: " + parkingPlaceDTO.getPlaceNumber() + " on tier: " + parkingPlaceDTO.getTier() + " already exists.");
        }
        parkingPlaceRepository.save(convertToParkingPlaceEntity(parkingPlaceDTO));
    }


    @Override
    public ParkingPlaceDTO getPlaceByPlaceNumberAndTier(int placeNumber, int tier) throws NotFoundException {
        ParkingPlace place = parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(placeNumber, tier).orElseThrow(() -> new NotFoundException("Place number: " + placeNumber + " on tier: " + tier + " is not found."));
        return convertParkingPlaceToDto(place);
    }

    @Override
    public void deletePlaceById(Long id) throws NotFoundException {
        if (!placeExists(id)) {
            throw new NotFoundException("Place with id: " + id + " is not found.");
        }
        parkingPlaceRepository.deleteById(id);
    }

    @Override
    public List<ParkingPlaceDTO> getAllFreePlaces() {
        List<ParkingPlace> parkingPlaces = parkingPlaceRepository.findAll();
        List<ParkingPlace> freePlaces = parkingPlaces.stream()
                .filter(parkingPlace -> parkingPlace.getReservation() == null)
                .collect(Collectors.toList());

        List<ParkingPlaceDTO> placeDTOS = new ArrayList<>();
        freePlaces.forEach(place -> {
            placeDTOS.add(convertParkingPlaceToDto(place));
        });
        return placeDTOS;
    }

    private ParkingPlaceDTO convertParkingPlaceToDto(ParkingPlace parkingPlace) {
        return mapper.map(parkingPlace, ParkingPlaceDTO.class);
    }

    private ParkingPlace convertToParkingPlaceEntity(ParkingPlaceDTO parkingPlaceDTO) {
        return mapper.map(parkingPlaceDTO, ParkingPlace.class);
    }

    private boolean placeExists(int placeNumber, int tier) {
        return parkingPlaceRepository.findParkingPlaceByPlaceNumberAndTier(placeNumber, tier).isPresent();
    }

    private boolean placeExists(Long id) {
        return parkingPlaceRepository.findById(id).isPresent();
    }

}
