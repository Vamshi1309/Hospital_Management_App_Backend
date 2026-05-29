package com.vamshi.HospitalManagementSystem.radiologist.services;

import com.vamshi.HospitalManagementSystem.radiologist.dtos.RadiologistProfileResponse;
import com.vamshi.HospitalManagementSystem.radiologist.dtos.UpdateRadiologistProfileRequest;

public interface RadiologistService {
    
    public RadiologistProfileResponse getMyProfile();

    public RadiologistProfileResponse updateMyProfile(UpdateRadiologistProfileRequest request);

}
