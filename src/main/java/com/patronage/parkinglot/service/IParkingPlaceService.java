package com.patronage.parkinglot.service;

import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.response.exception.AlreadyExistsException;
import com.patronage.parkinglot.response.exception.NotFoundException;

import java.util.List;

public interface IParkingPlaceService {
    public List<ParkingPlaceDTO> getPlaces();

    public void createNewPlace(ParkingPlaceDTO parkingLotDTO) throws AlreadyExistsException;

    public ParkingPlaceDTO getPlaceByPlaceNumberAndTier(int placeNumber, int tier) throws NotFoundException;

    public void deletePlaceById(Long id) throws NotFoundException;

    public List<ParkingPlaceDTO> getAllFreePlaces();
}

