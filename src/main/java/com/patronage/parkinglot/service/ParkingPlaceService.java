package com.patronage.parkinglot.service;

import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.response.exception.AlreadyExistsException;
import com.patronage.parkinglot.response.exception.NotFoundException;

import java.util.List;

public interface ParkingPlaceService {
    public List<ParkingPlaceDTO> getPlaces();

    public void createNewPlace(final ParkingPlaceDTO parkingLotDTO) throws AlreadyExistsException;

    public ParkingPlaceDTO getPlaceByPlaceNumberAndTier(final int placeNumber, final int tier) throws NotFoundException;

    public void deletePlaceById(final Long id) throws NotFoundException;

    public List<ParkingPlaceDTO> getAllFreePlaces();
}

