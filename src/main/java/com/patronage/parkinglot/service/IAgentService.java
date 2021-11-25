package com.patronage.parkinglot.service;

import com.patronage.parkinglot.model.DTO.AgentDTO;
import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.model.DTO.ReservationDTO;
import com.patronage.parkinglot.response.exception.AlreadyExistsException;
import com.patronage.parkinglot.response.exception.NotFoundException;

import java.util.List;

public interface IAgentService {
    public List<AgentDTO> getAgents();

    public void createNewAgent(AgentDTO agentDTO) throws AlreadyExistsException;

    public AgentDTO getAgentByID(Long id);

    public AgentDTO getAgentByName(String name) throws NotFoundException;

    public void deleteAgentByID(Long id);

    public void deleteAgentByName(String name) throws NotFoundException;

    public void createReservation(ReservationDTO reservationDTO) throws AlreadyExistsException, NotFoundException;

    public void deleteReservation(Long placeId) throws NotFoundException;

    public List<ParkingPlaceDTO> getAllReservedPlacesByAgent(String name) throws NotFoundException;

}
