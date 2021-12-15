package com.patronage.parkinglot.service.mapper;

import com.patronage.parkinglot.dto.AgentDto;
import com.patronage.parkinglot.dto.ParkingPlaceDto;
import com.patronage.parkinglot.dto.ReservationDto;
import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface Map {

    @Mapping(target = "name", source = "agent.name")
    AgentDto convertToAgentDto(final Agent agent);

    @Mapping(target = "name", source = "agentDto.name")
    Agent convertToAgentEntity(final AgentDto agentDto);

    @Mappings({
            @Mapping(target = "id", source = "place.id"),
            @Mapping(target = "placeNumber", source = "place.placeNumber"),
            @Mapping(target = "tier", source = "place.tier"),
            @Mapping(target = "placeForDisabledPeople", source = "place.placeForDisabledPeople")
    })
    ParkingPlaceDto convertParkingPlaceToDto(final ParkingPlace place);

    @Mappings({
            @Mapping(target = "id", source = "parkingPlaceDto.id"),
            @Mapping(target = "placeNumber", source = "parkingPlaceDto.placeNumber"),
            @Mapping(target = "tier", source = "parkingPlaceDto.tier"),
            @Mapping(target = "placeForDisabledPeople", source = "parkingPlaceDto.placeForDisabledPeople")
    })
    ParkingPlace convertToParkingPlaceEntity(final ParkingPlaceDto parkingPlaceDto);

    @Mappings({
            @Mapping(target = "id", source = "reservation.id"),
            @Mapping(target = "agentName", source = "agent.name"),
            @Mapping(target = "parkingPlaceId", source = "parkingPlace.id")
    })
    ReservationDto convertRepositoryToDto(final Reservation reservation);

    @Mappings({
            @Mapping(target = "id", source = "reservationDto.id"),
            @Mapping(target = "agent.name", source = "reservationDto.agentName"),
            @Mapping(target = "parkingPlace.id", source = "reservationDto.parkingPlaceId")
    })
    Reservation convertToReservationEntity(final ReservationDto reservationDto);

}
