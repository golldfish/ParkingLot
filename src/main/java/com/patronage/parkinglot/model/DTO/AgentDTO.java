package com.patronage.parkinglot.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentDTO {
    private Long id;
    private String name;
}
