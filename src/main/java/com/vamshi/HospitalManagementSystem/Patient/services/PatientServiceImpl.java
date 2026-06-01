package com.vamshi.HospitalManagementSystem.patient.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vamshi.HospitalManagementSystem.exceptions.ResourceNotFoundException;
import com.vamshi.HospitalManagementSystem.patient.dtos.PatientProfileResponse;
import com.vamshi.HospitalManagementSystem.patient.dtos.UpdatePatientProfileRequest;
import com.vamshi.HospitalManagementSystem.patient.entities.PatientProfileEntity;
import com.vamshi.HospitalManagementSystem.patient.repositories.PatientProfileRepository;
import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;
import com.vamshi.HospitalManagementSystem.user.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientProfileRepository patientRepository;
    private final UserRepository userRepository;

    @Override
    public PatientProfileResponse getMyProfile() {

        String phoneNumber = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        UserEntity user = userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found"));

        PatientProfileEntity patient = patientRepository
                .findByUserId(user.getId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Patient profile not found"));

        return mapToResponse(patient);
    }

    @Override
    public PatientProfileResponse updateMyProfile(
            UpdatePatientProfileRequest request) {

        String phoneNumber = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        UserEntity user = userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found"));

        PatientProfileEntity patient = patientRepository
                .findByUserId(user.getId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Patient profile not found"));

        if (request.getDateOfBirth() != null)
            patient.setDateOfBirth(request.getDateOfBirth());

        if (request.getBloodGroup() != null)
            patient.setBloodGroup(request.getBloodGroup());

        if (request.getEmergencyContact() != null)
            patient.setEmergencyContact(
                request.getEmergencyContact());

        if (request.getInsuranceInfo() != null)
            patient.setInsuranceInfo(request.getInsuranceInfo());

        PatientProfileEntity saved = patientRepository
                .save(patient);

        return mapToResponse(saved);
    }

    private PatientProfileResponse mapToResponse(
            PatientProfileEntity patient) {
        return PatientProfileResponse.builder()
                .userId(patient.getUser().getId())
                .name(patient.getUser().getName())
                .email(patient.getUser().getEmail())
                .phoneNumber(patient.getUser().getPhoneNumber())
                .dateOfBirth(patient.getDateOfBirth())
                .bloodGroup(patient.getBloodGroup())
                .emergencyContact(patient.getEmergencyContact())
                .insuranceInfo(patient.getInsuranceInfo())
                .build();
    }
}