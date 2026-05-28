package com.vamshi.HospitalManagementSystem.pharmacy.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispenseItemRequest {

    @NotNull(message = "Medicine id is required")
    private UUID medicineId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantityDispensed;
}
