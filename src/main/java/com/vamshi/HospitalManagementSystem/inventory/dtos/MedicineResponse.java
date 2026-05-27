package com.vamshi.HospitalManagementSystem.inventory.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineResponse {
    private UUID id;
    private String medicineName;
    private Integer quantity;
    private String unit;
    private LocalDate expiryDate;
    private Double price;
    private UUID addedById;
    private String addedByName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
