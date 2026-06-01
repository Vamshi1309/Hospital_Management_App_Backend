package com.vamshi.HospitalManagementSystem.pharmacist.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vamshi.HospitalManagementSystem.exceptions.ResourceNotFoundException;
import com.vamshi.HospitalManagementSystem.pharmacist.dtos.PharmacistProfileResponse;
import com.vamshi.HospitalManagementSystem.pharmacist.dtos.UpdatePharmacistProfileRequest;
import com.vamshi.HospitalManagementSystem.pharmacist.entities.PharmacistProfileEntity;
import com.vamshi.HospitalManagementSystem.pharmacist.repositories.PharmacistProfileRepository;
import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;
import com.vamshi.HospitalManagementSystem.user.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PharmacistServiceImpl implements PharmacistService {

    private final PharmacistProfileRepository pharmacistRepository;
    private final UserRepository userRepository;

    @Override
    public PharmacistProfileResponse getMyProfile() {

        String phoneNumber = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        UserEntity user = userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found"));

        PharmacistProfileEntity pharmacist = pharmacistRepository
                .findByUserId(user.getId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Pharmacist profile not found"));

        return mapToResponse(pharmacist);
    }

    @Override
    public PharmacistProfileResponse updateMyProfile(
            UpdatePharmacistProfileRequest request) {

        String phoneNumber = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        UserEntity user = userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found"));

        PharmacistProfileEntity pharmacist = pharmacistRepository
                .findByUserId(user.getId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Pharmacist profile not found"));

        if (request.getLicenseNumber() != null)
            pharmacist.setLicenseNumber(
                request.getLicenseNumber());

        PharmacistProfileEntity saved = pharmacistRepository
                .save(pharmacist);

        return mapToResponse(saved);
    }

    private PharmacistProfileResponse mapToResponse(
            PharmacistProfileEntity pharmacist) {
        return PharmacistProfileResponse.builder()
                .userId(pharmacist.getUser().getId())
                .name(pharmacist.getUser().getName())
                .email(pharmacist.getUser().getEmail())
                .phoneNumber(pharmacist.getUser().getPhoneNumber())
                .licenseNumber(pharmacist.getLicenseNumber())
                .build();
    }
}