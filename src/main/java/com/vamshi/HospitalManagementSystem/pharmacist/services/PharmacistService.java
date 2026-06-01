package com.vamshi.HospitalManagementSystem.pharmacist.services;

import com.vamshi.HospitalManagementSystem.pharmacist.dtos.PharmacistProfileResponse;
import com.vamshi.HospitalManagementSystem.pharmacist.dtos.UpdatePharmacistProfileRequest;

public interface PharmacistService {

    PharmacistProfileResponse getMyProfile();

    PharmacistProfileResponse updateMyProfile(
            UpdatePharmacistProfileRequest request);
}