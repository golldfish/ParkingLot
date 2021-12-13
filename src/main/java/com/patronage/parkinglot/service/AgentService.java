package com.patronage.parkinglot.service;

import com.patronage.parkinglot.DTO.AgentDTO;
import com.patronage.parkinglot.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.DTO.ReservationDTO;
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

@Service
@Transactional
@AllArgsConstructor
public class AgentService {
    private final AgentRepository agentRepository;
    private final ParkingPlaceRepository parkingPlaceRepository;
    private final ReservationRepository reservationRepository;
    //private final MapStructMapper mapper = new MapStructMapper();
    private final Map mapper = Mappers.getMapper(Map.class);

    public List<AgentDTO> getAgents() {
        final List<Agent> agents = agentRepository.findAll();

        final List<AgentDTO> agentDTOS = new ArrayList<>();
        agents.forEach(agent -> {
            agentDTOS.add(
                    mapper.convertToAgentDto(agent));
        });
        return agentDTOS;
    }


    public AgentDTO getAgentByID(final Long id) {
//        Agent agent = repository.findById(id).orElseThrow(() -> new AgentNotFoundException(id));
//        return convertToDto(agent);
        throw new NotYetImplementedException();
    }


    public AgentDTO getAgentByName(final String name) throws NotFoundException {
        final Agent agent = agentRepository.findAgentByName(name).orElseThrow(() -> new NotFoundException("Agent with name: " + name + " is not found."));
        return mapper.convertToAgentDto(agent);
    }


    public void createNewAgent(final AgentDTO agentDTO) throws AlreadyExistsException {
        if (agentExists(agentDTO.getName())) {
            throw new AlreadyExistsException("Agent with name: " + agentDTO.getName() + " already exists.");
        }
        agentRepository.save(mapper.convertToAgentEntity(agentDTO));
    }


    public void deleteAgentByID(final Long id) {
//            if (!idExists(id)) {
//                throw new AgentNotFoundException(id);
//            }
//            repository.deleteById(id);
        throw new NotYetImplementedException();
    }


    public void deleteAgentByName(final String name) throws NotFoundException {
        if (!agentExists(name)) {
            throw new NotFoundException("Agent with name: " + name + " is not found.");
        }
        agentRepository.deleteAgentByName(name);
    }


    public void createReservation(final ReservationDTO reservationDTO) throws AlreadyExistsException, NotFoundException {
        final Long placeId = reservationDTO.getParkingPlaceId();
        final String agentName = reservationDTO.getAgentName();
        if (reservationExists(placeId)) {
            throw new AlreadyExistsException("Reservation of place: " + placeId + " already exists.");
        }
        final ParkingPlace parkingPlace = parkingPlaceRepository.findById(placeId).orElseThrow(() -> new NotFoundException("Place with id: " + placeId + " is not found."));
        final Agent agent = agentRepository.findAgentByName(agentName).orElseThrow(() -> new NotFoundException("Agent with name: " + agentName + " is not found."));
        final Reservation reservation = new Reservation();
        reservation.setAgent(agent);
        reservation.setParkingPlace(parkingPlace);
        reservationRepository.save(reservation);
    }


    public void deleteReservation(final Long placeId) throws NotFoundException {
        if (!reservationExists(placeId)) {
            throw new NotFoundException("Reservation with placeId: " + placeId + " is not found.");
        }
        reservationRepository.deleteByParkingPlaceId(placeId);
    }


    public List<ParkingPlaceDTO> getAllReservedPlacesByAgent(final String name) throws NotFoundException {
        if (!agentExists(name)) {
            throw new NotFoundException("Agent with name: " + name + " is not found.");
        }
        final List<Reservation> reservations = reservationRepository.findReservationByAgent_Name(name);
        final List<ParkingPlaceDTO> parkingPlaceDTOS = new ArrayList<>();
        reservations.forEach(reservation -> {
            parkingPlaceDTOS.add(mapper.convertParkingPlaceToDTO(reservation.getParkingPlace()));
        });
        return parkingPlaceDTOS;
    }

    private boolean agentExists(final String name) {
        return agentRepository.findAgentByName(name).isPresent();
    }

    private boolean idExists(final Long id) {
        return agentRepository.findById(id).isPresent();
    }

    private boolean reservationExists(final Long id) {
        return reservationRepository.findReservationByParkingPlace_Id(id).isPresent();
    }


}