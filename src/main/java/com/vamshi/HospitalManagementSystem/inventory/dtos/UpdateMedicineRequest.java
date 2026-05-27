package com.vamshi.HospitalManagementSystem.inventory.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateMedicineRequest {
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    private String unit;

    private LocalDate expiryDate;

    private Double price;
}
