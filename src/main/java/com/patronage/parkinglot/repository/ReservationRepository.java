package com.patronage.parkinglot.repository;

import com.patronage.parkinglot.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

