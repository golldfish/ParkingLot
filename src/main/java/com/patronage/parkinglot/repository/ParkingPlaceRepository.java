package com.patronage.parkinglot.repository;

import com.patronage.parkinglot.model.ParkingPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Long> {
    Optional<ParkingPlace> findParkingPlaceByPlaceNumberAndTier(int placeNumber, int tier);

}
