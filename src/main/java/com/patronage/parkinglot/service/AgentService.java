package com.patronage.parkinglot.service;

import com.patronage.parkinglot.model.DTO.AgentDTO;
import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.model.DTO.ReservationDTO;
import com.patronage.parkinglot.response.exception.AlreadyExistsException;
import com.patronage.parkinglot.response.exception.NotFoundException;

import java.util.List;

public interface AgentService {
    public List<AgentDTO> getAgents();

    public void createNewAgent(final AgentDTO agentDTO) throws AlreadyExistsException;

    public AgentDTO getAgentByID(final Long id);

    public AgentDTO getAgentByName(final String name) throws NotFoundException;

    public void deleteAgentByID(final Long id);

    public void deleteAgentByName(final String name) throws NotFoundException;

    public void createReservation(final ReservationDTO reservationDTO) throws AlreadyExistsException, NotFoundException;

    public void deleteReservation(final Long placeId) throws NotFoundException;

    public List<ParkingPlaceDTO> getAllReservedPlacesByAgent(final String name) throws NotFoundException;

}
