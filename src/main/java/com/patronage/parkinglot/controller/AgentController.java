package com.patronage.parkinglot.controller;

import com.patronage.parkinglot.dto.AgentDto;
import com.patronage.parkinglot.dto.ParkingPlaceDto;
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
    ResponseEntity<List<AgentDto>> all() {
        return new ResponseEntity<>(agentService.getAgents(), HttpStatus.OK);
    }


    @GetMapping("/agents/{name}")
    ResponseEntity<AgentDto> getAgentByName(@PathVariable final String name) throws NotFoundException {
        return new ResponseEntity<>(agentService.getAgentByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/agents", method = RequestMethod.POST)
    ResponseEntity<Void> newAgent(@RequestParam("name") final AgentDto agentDto) throws AlreadyExistsException {
        agentService.createNewAgent(agentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/agents/{name}")
    ResponseEntity<String> deleteAgentByName(@PathVariable final String name) throws NotFoundException {
        agentService.deleteAgentByName(name);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }


    @RequestMapping(value = "/agents/reservations", method = RequestMethod.POST)
    ResponseEntity<Void> newReservation(@RequestParam("name") final String agentName, @RequestParam("place") final Long placeId) throws AlreadyExistsException, NotFoundException {
        agentService.createReservation(agentName, placeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/agents/reservations/{placeId}")
    ResponseEntity<String> deleteAgentByName(@PathVariable final Long placeId) throws NotFoundException {
        agentService.deleteReservation(placeId);
        return new ResponseEntity<>("Reservation successfully deleted", HttpStatus.OK);
    }


    @GetMapping("/agents/reservations/{name}")
    ResponseEntity<List<ParkingPlaceDto>> all(@PathVariable final String name) throws NotFoundException {
        return new ResponseEntity<>(agentService.getAllReservedPlacesByAgent(name), HttpStatus.OK);
    }
}
