package com.patronage.parkinglot.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@Table(name = "agent")
public class Agent {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "agent_name", unique = true)
    @Size(min = 2, max = 20)
    @NotBlank
    @NotNull
    private String name;

    @OneToOne(mappedBy = "agent")
    private Reservation reservation;

}
