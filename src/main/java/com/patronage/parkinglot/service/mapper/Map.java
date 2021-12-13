package com.patronage.parkinglot.service.mapper;

import com.patronage.parkinglot.DTO.AgentDTO;
import com.patronage.parkinglot.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.DTO.ReservationDTO;
import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface Map {

    @Mapping(target = "name", source = "agent.name")
    AgentDTO convertToAgentDto(final Agent agent);

    @Mapping(target = "name", source = "agentDTO.name")
    Agent convertToAgentEntity(final AgentDTO agentDTO);

    @Mappings({
            @Mapping(target = "id", source = "place.id"),
            @Mapping(target = "placeNumber", source = "place.placeNumber"),
            @Mapping(target = "tier", source = "place.tier"),
            @Mapping(target = "placeForDisabledPeople", source = "place.placeForDisabledPeople")
    })
    ParkingPlaceDTO convertParkingPlaceToDTO(final ParkingPlace place);

    @Mappings({
            @Mapping(target = "id", source = "parkingPlaceDTO.id"),
            @Mapping(target = "placeNumber", source = "parkingPlaceDTO.placeNumber"),
            @Mapping(target = "tier", source = "parkingPlaceDTO.tier"),
            @Mapping(target = "placeForDisabledPeople", source = "parkingPlaceDTO.placeForDisabledPeople")
    })
    ParkingPlace convertToParkingPlaceEntity(final ParkingPlaceDTO parkingPlaceDTO);

    @Mappings({
            @Mapping(target = "id", source = "reservation.id"),
            @Mapping(target = "agentName", source = "agent.name"),
            @Mapping(target = "parkingPlaceId", source = "parkingPlace.id")
    })
    ReservationDTO convertRepositoryToDTO(final Reservation reservation);

    @Mappings({
            @Mapping(target = "id", source = "reservationDTO.id"),
            @Mapping(target = "agent.name", source = "reservationDTO.agentName"),
            @Mapping(target = "parkingPlace.id", source = "reservationDTO.parkingPlaceId")
    })
    Reservation convertToReservationEntity(final ReservationDTO reservationDTO);

}
