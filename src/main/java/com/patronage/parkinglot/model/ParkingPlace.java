package com.patronage.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "parking_place")
public class ParkingPlace {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "place_number")
    private int placeNumber;

    @NotNull
    private int tier;

    @NotNull
    private boolean placeForDisabledPeople;

    @NotNull
    private boolean isReserved;

    @OneToOne(mappedBy = "parkingPlace")
    private Reservation reservation;

}
