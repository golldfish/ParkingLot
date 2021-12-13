package com.patronage.parkinglot.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AgentDTO {
    private final String name;
}
