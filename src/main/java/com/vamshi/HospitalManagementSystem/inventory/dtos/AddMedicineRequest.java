package com.vamshi.HospitalManagementSystem.inventory.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddMedicineRequest {

    @NotBlank(message = "Medicine name is required")
    private String medicineName;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotBlank(message = "Unit is required")
    private String unit;

    private LocalDate expiryDate;

    private Double price;
}
