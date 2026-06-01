package com.vamshi.HospitalManagementSystem.patient.services;

import com.vamshi.HospitalManagementSystem.patient.dtos.PatientProfileResponse;
import com.vamshi.HospitalManagementSystem.patient.dtos.UpdatePatientProfileRequest;

public interface PatientService {

    PatientProfileResponse getMyProfile();

    PatientProfileResponse updateMyProfile(
            UpdatePatientProfileRequest request);
}