package com.patronage.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "parking_lot")
public class ParkingLot {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "place_number")
    private int parkingPlaceNumber;

    @Size(min = 1, max = 2)
    @NotBlank
    @NotNull
    private int tier;

    @NotNull
    private boolean placeForDisabledPeople;

    @OneToOne(mappedBy = "parkingLot")
    private Reservation reservation;

}
