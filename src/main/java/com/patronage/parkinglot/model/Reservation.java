package com.patronage.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parkingPlace", referencedColumnName = "id")
    private ParkingPlace parkingPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent", referencedColumnName = "id")
    private Agent agent;


}
