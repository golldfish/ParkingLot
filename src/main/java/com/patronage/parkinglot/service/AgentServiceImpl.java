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
import com.patronage.parkinglot.service.mapper.MapStructMapperImpl;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final ParkingPlaceRepository parkingPlaceRepository;
    private final ReservationRepository reservationRepository;
    private final MapStructMapperImpl mapper = new MapStructMapperImpl();

    @Override
    public List<AgentDTO> getAgents() {
        final List<Agent> agents = agentRepository.findAll();

        final List<AgentDTO> agentDTOS = new ArrayList<>();
        agents.forEach(agent -> {
            agentDTOS.add(mapper.convertAgentToDto(agent));
        });
        return agentDTOS;
    }

    @Override
    public AgentDTO getAgentByID(final Long id) {
//        Agent agent = repository.findById(id).orElseThrow(() -> new AgentNotFoundException(id));
//        return convertToDto(agent);
        throw new NotYetImplementedException();
    }

    @Override
    public AgentDTO getAgentByName(final String name) throws NotFoundException {
        final Agent agent = agentRepository.findAgentByName(name).orElseThrow(() -> new NotFoundException("Agent with name: " + name + " is not found."));
        return mapper.convertAgentToDto(agent);
    }

    @Override
    public void createNewAgent(final AgentDTO agentDTO) throws AlreadyExistsException {
        if (agentExists(agentDTO.getName())) {
            throw new AlreadyExistsException("Agent with name: " + agentDTO.getName() + " already exists.");
        }
        agentRepository.save(mapper.convertToAgentEntity(agentDTO));
    }


    @Override
    public void deleteAgentByID(final Long id) {
//            if (!idExists(id)) {
//                throw new AgentNotFoundException(id);
//            }
//            repository.deleteById(id);
        throw new NotYetImplementedException();
    }


    @Override
    public void deleteAgentByName(final String name) throws NotFoundException {
        if (!agentExists(name)) {
            throw new NotFoundException("Agent with name: " + name + " is not found.");
        }
        agentRepository.deleteAgentByName(name);
    }

    @Override
    public void createReservation(final ReservationDTO reservationDTO) throws AlreadyExistsException, NotFoundException {
        final Long placeId = reservationDTO.getParkingPlaceDTO().getId();
        final String agentName = reservationDTO.getAgentDTO().getName();
        if (reservationExists(placeId)) {
            throw new AlreadyExistsException("Reservation of place: " + placeId + " already exists.");
        }
        final ParkingPlace parkingLot = parkingPlaceRepository.findById(placeId).orElseThrow(() -> new NotFoundException("Place with id: " + placeId + " is not found."));
        final Agent agent = agentRepository.findAgentByName(agentName).orElseThrow(() -> new NotFoundException("Agent with name: " + agentName + " is not found."));
        final Reservation reservation = new Reservation();
        reservation.setAgent(agent);
        reservation.setParkingPlace(parkingLot);
        reservationRepository.save(reservation);
        parkingLot.setReserved(true);
    }

    @Override
    public void deleteReservation(final Long placeId) throws NotFoundException {
        if (!reservationExists(placeId)) {
            throw new NotFoundException("Reservation with placeId: " + placeId + " is not found.");
        }
        reservationRepository.deleteByParkingPlaceId(placeId);
    }

    @Override
    public List<ParkingPlaceDTO> getAllReservedPlacesByAgent(final String name) throws NotFoundException {
        if (!agentExists(name)) {
            throw new NotFoundException("Agent with name: " + name + " is not found.");
        }
        final List<Reservation> reservations = reservationRepository.findReservationByAgent_Name(name);
        final List<ParkingPlaceDTO> parkingLotDTOS = new ArrayList<>();
        reservations.forEach(reservation -> {
            parkingLotDTOS.add(mapper.convertParkingPlaceToDTO(reservation.getParkingPlace()));
        });
        return parkingLotDTOS;
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