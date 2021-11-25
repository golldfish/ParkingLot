package com.patronage.parkinglot.model.DTO;

import lombok.Data;

@Data
public class ParkingPlaceDTO {
    private Long id;
    private int placeNumber;
    private int tier;
    private boolean placeForDisabledPeople;
}
