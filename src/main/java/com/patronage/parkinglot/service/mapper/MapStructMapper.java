package com.patronage.parkinglot.service.mapper;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.DTO.AgentDTO;
import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.model.DTO.ReservationDTO;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.model.Reservation;

public interface MapStructMapper {

    public AgentDTO convertAgentToDto(final Agent agent);

    public Agent convertToAgentEntity(final AgentDTO agentDTO);

    public ParkingPlaceDTO convertParkingPlaceToDTO(final ParkingPlace place);

    public ParkingPlace convertToParkingPlaceEntity(final ParkingPlaceDTO parkingPlaceDTO);

    public ReservationDTO convertRepositoryToDTO(final Reservation reservation);

    public Reservation convertToReservationEntity(final ReservationDTO reservationDTO);


}
