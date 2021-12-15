package com.patronage.parkinglot.controller;

import com.patronage.parkinglot.dto.ParkingPlaceDto;
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
    ResponseEntity<List<ParkingPlaceDto>> all() {
        return new ResponseEntity<>(parkingPlaceService.getPlaces(), HttpStatus.OK);
    }

    @GetMapping("/places/{tier}/{place}")
    ResponseEntity<ParkingPlaceDto> getPlaceByTierAndPlace(@PathVariable final int tier, @PathVariable final int place) throws NotFoundException {
        return new ResponseEntity<>(parkingPlaceService.getPlaceByPlaceNumberAndTier(tier, place), HttpStatus.OK);
    }

    @RequestMapping(value = "/places")
    @PostMapping("")
    ResponseEntity<Void> newPlace(@RequestParam("nr") final int placeNumber, @RequestParam("tier") final int tier, @RequestParam("isDisabled") final boolean isDisabled) throws AlreadyExistsException {
        parkingPlaceService.createNewPlace(placeNumber, tier, isDisabled);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/places/{id}")
    ResponseEntity<String> deletePlaceById(@PathVariable final Long id) throws NotFoundException {
        parkingPlaceService.deletePlaceById(id);
        return new ResponseEntity<>("Place successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/free-places")
    ResponseEntity<List<ParkingPlaceDto>> getFreePlaces() {
        return new ResponseEntity<>(parkingPlaceService.getAllFreePlaces(), HttpStatus.OK);
    }
}
