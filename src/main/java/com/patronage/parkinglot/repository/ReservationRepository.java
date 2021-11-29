package com.patronage.parkinglot.repository;

import com.patronage.parkinglot.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findReservationByParkingPlace_Id(Long id);

    List<Reservation> findReservationByAgent_Name(String name);

    void deleteByParkingPlaceId(Long id);

}

