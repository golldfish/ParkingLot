package com.patronage.parkinglot.service;

import com.patronage.parkinglot.dto.AgentDto;
import com.patronage.parkinglot.dto.ParkingPlaceDto;
import com.patronage.parkinglot.exception.AlreadyExistsException;
import com.patronage.parkinglot.exception.NotFoundException;
import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.model.Reservation;
import com.patronage.parkinglot.repository.AgentRepository;
import com.patronage.parkinglot.repository.ParkingPlaceRepository;
import com.patronage.parkinglot.repository.ReservationRepository;
import com.patronage.parkinglot.service.mapper.Map;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AgentService {
    private final AgentRepository agentRepository;
    private final ParkingPlaceRepository parkingPlaceRepository;
    private final ReservationRepository reservationRepository;
    private final Map mapper = Mappers.getMapper(Map.class);

    public List<AgentDto> getAgents() {
        final List<Agent> agents = agentRepository.findAll();

        final List<AgentDto> agentDtos = new ArrayList<>();
        agents.forEach(agent -> {
            agentDtos.add(mapper.convertToAgentDto(agent));
        });
        return agentDtos;
    }


    public AgentDto getAgentByID(final Long id) {
//        Agent agent = repository.findById(id).orElseThrow(() -> new AgentNotFoundException(id));
//        return convertToDto(agent);
        throw new NotYetImplementedException();
    }


    public AgentDto getAgentByName(final String name) throws NotFoundException {
        final Agent agent = agentRepository.findAgentByName(name).orElseThrow(() -> new NotFoundException(String.format("Agent with name: %s is not found.", name)));
        return mapper.convertToAgentDto(agent);
    }


    public void createNewAgent(final AgentDto agentDto) throws AlreadyExistsException {
        agentRepository.findAll().stream().map(Agent::getName).filter(a -> a.equals(agentDto.getName())).findFirst().ifPresentOrElse(agent -> {
            throw new AlreadyExistsException(String.format("Agent with name %s already exists.", agentDto.getName()));
        }, () -> agentRepository.save(mapper.convertToAgentEntity(agentDto)));
    }


    public void deleteAgentByID(final Long id) {
//            if (!idExists(id)) {
//                throw new AgentNotFoundException(id);
//            }
//            repository.deleteById(id);
        throw new NotYetImplementedException();
    }


    public void deleteAgentByName(final String name) throws NotFoundException {
        agentRepository.findAgentByName(name).ifPresentOrElse(agent -> {
            agentRepository.deleteAgentByName(name);
        }, () -> {
            throw new NotFoundException(String.format("Agent with name: %s is not found.", name));
        });
    }


    public void createReservation(final String agentName, final Long placeId) throws AlreadyExistsException, NotFoundException {
        reservationRepository.findAll().stream().map(Reservation::getParkingPlace).filter(a -> Objects.equals(a.getId(), placeId)).findFirst().ifPresent(reservation -> {
            throw new AlreadyExistsException(String.format("Reservation of place: %s already exists.", placeId));
        });

        final ParkingPlace parkingPlace = parkingPlaceRepository.findById(placeId).orElseThrow(() -> new NotFoundException(String.format("Place with id: %s is not found.", placeId)));
        final Agent agent = agentRepository.findAgentByName(agentName).orElseThrow(() -> new NotFoundException(String.format("Agent with name: %s is not found.", agentName)));
        final Reservation reservation = new Reservation(UUID.randomUUID().getMostSignificantBits(), agent, parkingPlace);
        reservationRepository.save(reservation);
    }


    public void deleteReservation(final Long placeId) throws NotFoundException {
        reservationRepository.findReservationByParkingPlace_Id(placeId).ifPresentOrElse(reservation -> {
            reservationRepository.deleteByParkingPlaceId(placeId);
        }, () -> {
            throw new NotFoundException(String.format("Reservation with placeId: %s is not found.", placeId));
        });
    }


    public List<ParkingPlaceDto> getAllReservedPlacesByAgent(final String name) throws NotFoundException {
        agentRepository.findAll().stream().map(Agent::getName).filter(a -> a.equals(name)).findFirst().orElseThrow(() -> {
            throw new NotFoundException(String.format("Agent with name: %s is not found.", name));
        });

        final List<Reservation> reservations = reservationRepository.findReservationByAgent_Name(name);
        final List<ParkingPlaceDto> parkingPlaceDtos = new ArrayList<>();
        reservations.forEach(reservation -> {
            parkingPlaceDtos.add(mapper.convertParkingPlaceToDto(reservation.getParkingPlace()));
        });
        return parkingPlaceDtos;
    }
}