package com.vamshi.HospitalManagementSystem.pharmacy.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispenseMedicineRequest {

    @NotNull(message = "Prescription id is required")
    private UUID prescriptionId;

    @NotEmpty(message = "At least one medicine required")
    private List<DispenseItemRequest> items;

    private String notes;
}
