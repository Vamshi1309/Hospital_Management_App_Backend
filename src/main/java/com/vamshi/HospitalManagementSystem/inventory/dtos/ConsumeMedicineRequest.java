package com.vamshi.HospitalManagementSystem.inventory.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumeMedicineRequest {

    @NotNull(message = "quantity is required")
    private Integer quantity;
}
