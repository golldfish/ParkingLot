package com.patronage.parkinglot.service;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.DTO.AgentDTO;
import com.patronage.parkinglot.model.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.model.DTO.ReservationDTO;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.model.Reservation;
import com.patronage.parkinglot.repository.AgentRepository;
import com.patronage.parkinglot.repository.ParkingPlaceRepository;
import com.patronage.parkinglot.repository.ReservationRepository;
import com.patronage.parkinglot.response.exception.AlreadyExistsException;
import com.patronage.parkinglot.response.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AgentService implements IAgentService {
    private final AgentRepository agentRepository;
    private final ParkingPlaceRepository parkingLotRepository;
    private final ReservationRepository reservationRepository;
    private final ModelMapper mapper;

    @Override
    public List<AgentDTO> getAgents() {
        List<Agent> agents = agentRepository.findAll();

        List<AgentDTO> agentDTOS = new ArrayList<>();
        agents.forEach(agent -> {
            agentDTOS.add(convertAgentToDto(agent));
        });
        return agentDTOS;
    }

    @Override
    public AgentDTO getAgentByID(Long id) {
//        Agent agent = repository.findById(id).orElseThrow(() -> new AgentNotFoundException(id));
//        return convertToDto(agent);
        throw new NotYetImplementedException();
    }

    @Override
    public AgentDTO getAgentByName(String name) throws NotFoundException {
        Agent agent = agentRepository.findAgentByName(name).orElseThrow(() -> new NotFoundException("Agent with name: " + name + " is not found."));
        return convertAgentToDto(agent);
    }

    @Override
    public void createNewAgent(AgentDTO agentDTO) throws AlreadyExistsException {
        if (agentExists(agentDTO.getName())) {
            throw new AlreadyExistsException("Agent with name: " + agentDTO.getName() + " already exists.");
        }
        agentRepository.save(convertToAgentEntity(agentDTO));
    }


    @Override
    public void deleteAgentByID(Long id) {
//            if (!idExists(id)) {
//                throw new AgentNotFoundException(id);
//            }
//            repository.deleteById(id);
        throw new NotYetImplementedException();
    }


    @Override
    public void deleteAgentByName(String name) throws NotFoundException {
        if (!agentExists(name)) {
            throw new NotFoundException("Agent with name: " + name + " is not found.");
        }
        agentRepository.deleteAgentByName(name);

    }

    @Override
    public void createReservation(ReservationDTO reservationDTO) throws AlreadyExistsException, NotFoundException {
        Long placeId = reservationDTO.getParkingLotDTO().getId();
        String agentName = reservationDTO.getAgentDTO().getName();
        if (reservationExists(placeId)) {
            throw new AlreadyExistsException("Reservation of place: " + placeId + " already exists.");
        }
        ParkingPlace parkingLot = parkingLotRepository.findById(placeId).orElseThrow(() -> new NotFoundException("Place with id: " + placeId + " is not found."));
        Agent agent = agentRepository.findAgentByName(agentName).orElseThrow(() -> new NotFoundException("Agent with name: " + agentName + " is not found."));
        Reservation reservation = new Reservation();
        reservation.setAgent(agent);
        reservation.setParkingLot(parkingLot);
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long placeId) throws NotFoundException {
        if (!reservationExists(placeId)) {
            throw new NotFoundException("Reservation with placeId: " + placeId + " is not found.");
        }
        reservationRepository.deleteByParkingLotId(placeId);
    }

    @Override
    public List<ParkingPlaceDTO> getAllReservedPlacesByAgent(String name) throws NotFoundException {
        if (!agentExists(name)) {
            throw new NotFoundException("Agent with name: " + name + " is not found.");
        }
        List<Reservation> reservations = reservationRepository.findReservationByAgent_Name(name);
        List<ParkingPlaceDTO> parkingLotDTOS = new ArrayList<>();
        reservations.forEach(reservation -> {
            parkingLotDTOS.add(convertParkingLotToDTO(reservation.getParkingLot()));
        });
        return parkingLotDTOS;
    }

    private boolean agentExists(String name) {
        return agentRepository.findAgentByName(name).isPresent();
    }

    private boolean idExists(Long id) {
        return agentRepository.findById(id).isPresent();
    }

    private boolean reservationExists(Long id) {
        return reservationRepository.findReservationByParkingLot_Id(id).isPresent();
    }

    private AgentDTO convertAgentToDto(Agent agent) {
        return mapper.map(agent, AgentDTO.class);
    }

    private Agent convertToAgentEntity(AgentDTO agentDTO) {
        return mapper.map(agentDTO, Agent.class);
    }

    private ParkingPlaceDTO convertParkingLotToDTO(ParkingPlace parkingLot) {
        return mapper.map(parkingLot, ParkingPlaceDTO.class);
    }

}