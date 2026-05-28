package com.vamshi.HospitalManagementSystem.pharmacy.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispenseResponse {
    private UUID dispenseId;

    private UUID prescriptionId;

    private UUID patientId;
    private String patientName;

    private UUID dispensedById;
    private String dispensedByName;

    private List<DispenseItemResponse> items;

    // bill
    private Double totalAmount; // ← added

    private String notes;
    private LocalDateTime dispensedAt;
}
