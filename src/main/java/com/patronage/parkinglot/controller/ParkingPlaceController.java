package com.patronage.parkinglot.controller;

import com.patronage.parkinglot.DTO.ParkingPlaceDTO;
import com.patronage.parkinglot.exception.AlreadyExistsException;
import com.patronage.parkinglot.exception.NotFoundException;
import com.patronage.parkinglot.service.ParkingPlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ParkingPlaceController {

    private ParkingPlaceService parkingPlaceService;

    @GetMapping("/places")
    ResponseEntity<List<ParkingPlaceDTO>> all() {
        return new ResponseEntity<>(parkingPlaceService.getPlaces(), HttpStatus.OK);
    }

    @GetMapping("/places/{tier}/{place}")
    ResponseEntity<ParkingPlaceDTO> getPlaceByTierAndPlace(@PathVariable final int tier, @PathVariable final int place) throws NotFoundException {
        return new ResponseEntity<>(parkingPlaceService.getPlaceByPlaceNumberAndTier(tier, place), HttpStatus.OK);
    }

    @PostMapping("/places")
    ResponseEntity<Void> newPlace(@RequestBody final ParkingPlaceDTO newPlace) throws AlreadyExistsException {
        parkingPlaceService.createNewPlace(newPlace);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/places/{id}")
    ResponseEntity<String> deletePlaceById(@PathVariable final Long id) throws NotFoundException {
        parkingPlaceService.deletePlaceById(id);
        return new ResponseEntity<>("Place successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/free-places")
    ResponseEntity<List<ParkingPlaceDTO>> getFreePlaces() {
        return new ResponseEntity<>(parkingPlaceService.getAllFreePlaces(), HttpStatus.OK);
    }
}
