package com.vamshi.HospitalManagementSystem.doctor.dtos;

import lombok.Data;

@Data
public class UpdateDoctorProfileRequest {
    private String specialization;
    private String qualification;
    private Integer experienceInYears;
    private String availabilityJson;
}
