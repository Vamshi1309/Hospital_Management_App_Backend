package com.vamshi.HospitalManagementSystem.patient.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfileResponse {

    private UUID userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String bloodGroup;
    private String emergencyContact;
    private String insuranceInfo;
}