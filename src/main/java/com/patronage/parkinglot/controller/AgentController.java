package com.patronage.parkinglot.controller;

import com.patronage.parkinglot.DTO.AgentDTO;
import com.patronage.parkinglot.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.DTO.ReservationDTO;
import com.patronage.parkinglot.exception.AlreadyExistsException;
import com.patronage.parkinglot.exception.NotFoundException;
import com.patronage.parkinglot.service.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @GetMapping("/agents")
    ResponseEntity<List<AgentDTO>> all() {
        return new ResponseEntity<>(agentService.getAgents(), HttpStatus.OK);
    }


    @GetMapping("/agents/{name}")
    ResponseEntity<AgentDTO> getAgentByName(@PathVariable final String name) throws NotFoundException {
        return new ResponseEntity<>(agentService.getAgentByName(name), HttpStatus.OK);
    }

    @PostMapping("/agents")
    ResponseEntity<Void> newAgent(@RequestBody final AgentDTO newAgent) throws AlreadyExistsException {
        agentService.createNewAgent(newAgent);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/agents/{name}")
    ResponseEntity<String> deleteAgentByName(@PathVariable final String name) throws NotFoundException {
        agentService.deleteAgentByName(name);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

    @PostMapping("/agents/reservations")
    ResponseEntity<Void> newReservation(@RequestBody final ReservationDTO newReservation) throws AlreadyExistsException, NotFoundException {
        agentService.createReservation(newReservation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/agents/reservations/{placeId}")
    ResponseEntity<String> deleteAgentByName(@PathVariable final Long placeId) throws NotFoundException {
        agentService.deleteReservation(placeId);
        return new ResponseEntity<>("Reservation successfully deleted", HttpStatus.OK);
    }
    /*

    @GetMapping("/agents/reservations/{name}")
    ResponseEntity<List<ParkingPlaceDTO>> all(@PathVariable final String name) throws NotFoundException {
        return new ResponseEntity<>(agentService.getAllReservedPlacesByAgent(name), HttpStatus.OK);
    }*/


}
