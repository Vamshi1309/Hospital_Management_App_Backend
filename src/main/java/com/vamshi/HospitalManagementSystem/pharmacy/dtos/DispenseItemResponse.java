package com.vamshi.HospitalManagementSystem.pharmacy.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispenseItemResponse {

    private UUID dispenseId;
    private UUID medicineId;
    private String medicineName;
    private Integer quantityDispensed;
    private Double pricePerUnit;
    private Double totalPrice; // quantity * pricePerUnit
}
