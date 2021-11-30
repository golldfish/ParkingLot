package com.patronage.parkinglot.service.mapper;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.DTO.AgentDTO;
import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.model.DTO.ReservationDTO;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.model.Reservation;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MapStructMapperImpl implements MapStructMapper {
    @Override
    public AgentDTO convertAgentToDto(final Agent agent) {
        if (agent == null) {
            return null;
        }
        return new AgentDTO(agent.getId(), agent.getName());
    }

    @Override
    public Agent convertToAgentEntity(final AgentDTO agentDTO) {
        if (agentDTO == null) {
            return null;
        }
        final Agent agent = new Agent();
        agent.setId(agentDTO.getId());
        agent.setName(agentDTO.getName());
        return agent;
    }

    @Override
    public ParkingPlaceDTO convertParkingPlaceToDTO(final ParkingPlace place) {
        if (place == null) {
            return null;
        }
        return new ParkingPlaceDTO(place.getId(), place.getPlaceNumber(), place.getTier(), place.isPlaceForDisabledPeople(), place.isReserved());
    }

    @Override
    public ParkingPlace convertToParkingPlaceEntity(final ParkingPlaceDTO parkingPlaceDTO) {
        if (parkingPlaceDTO == null) {
            return null;
        }
        final ParkingPlace place = new ParkingPlace();
        place.setId(parkingPlaceDTO.getId());
        place.setPlaceNumber(parkingPlaceDTO.getPlaceNumber());
        place.setTier(parkingPlaceDTO.getTier());
        place.setPlaceForDisabledPeople(parkingPlaceDTO.isPlaceForDisabledPeople());
        place.setReserved(parkingPlaceDTO.isReserved());
        return place;
    }

    @Override
    public ReservationDTO convertRepositoryToDTO(final Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        return new ReservationDTO(reservation.getId(), convertAgentToDto(reservation.getAgent()), convertParkingPlaceToDTO(reservation.getParkingPlace()));
    }

    @Override
    public Reservation convertToReservationEntity(final ReservationDTO reservationDTO) {
        if (reservationDTO == null) {
            return null;
        }
        final Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setAgent(convertToAgentEntity(reservationDTO.getAgentDTO()));
        reservation.setParkingPlace(convertToParkingPlaceEntity(reservationDTO.getParkingPlaceDTO()));
        return reservation;
    }
}
