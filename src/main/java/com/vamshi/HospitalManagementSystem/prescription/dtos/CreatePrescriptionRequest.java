package com.vamshi.HospitalManagementSystem.prescription.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePrescriptionRequest {
    
    @NotNull(message = "appointment Id is mandatory")
    private UUID appointmentId;

    private String notes;

    @NotEmpty(message = "Prescriptions menu is required")
    private List<PrescriptionItemRequest> items;
}
