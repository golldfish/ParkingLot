package com.patronage.parkinglot.service.mapper;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.DTO.AgentDTO;
import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.model.ParkingPlace;
import org.modelmapper.ModelMapper;

public class Mapper {

    private final static ModelMapper mapper = new ModelMapper();

    public static AgentDTO convertAgentToDto(Agent agent) {
        return mapper.map(agent, AgentDTO.class);
    }

    public static Agent convertToAgentEntity(AgentDTO agentDTO) {
        return mapper.map(agentDTO, Agent.class);
    }

    public static ParkingPlaceDTO convertParkingPlaceToDTO(ParkingPlace parkingLot) {
        return mapper.map(parkingLot, ParkingPlaceDTO.class);
    }

    public static ParkingPlace convertToParkingPlaceEntity(ParkingPlaceDTO parkingPlaceDTO) {
        return mapper.map(parkingPlaceDTO, ParkingPlace.class);
    }

}
