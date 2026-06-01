package com.vamshi.HospitalManagementSystem.pharmacist.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vamshi.HospitalManagementSystem.common.ApiResponse;
import com.vamshi.HospitalManagementSystem.pharmacist.dtos.PharmacistProfileResponse;
import com.vamshi.HospitalManagementSystem.pharmacist.dtos.UpdatePharmacistProfileRequest;
import com.vamshi.HospitalManagementSystem.pharmacist.services.PharmacistService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pharmacist")
@RequiredArgsConstructor
public class PharmacistController {

    private final PharmacistService pharmacistService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<PharmacistProfileResponse>> getMyProfile() {

        PharmacistProfileResponse response = pharmacistService
                .getMyProfile();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Profile fetched successfully", response));
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<PharmacistProfileResponse>> updateMyProfile(
            @Valid @RequestBody UpdatePharmacistProfileRequest request) {

        PharmacistProfileResponse response = pharmacistService
                .updateMyProfile(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Profile updated successfully", response));
    }
}