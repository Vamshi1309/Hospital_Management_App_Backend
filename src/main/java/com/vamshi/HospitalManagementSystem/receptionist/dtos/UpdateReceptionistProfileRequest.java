package com.vamshi.HospitalManagementSystem.receptionist.dtos;

import com.vamshi.HospitalManagementSystem.common.enums.Shifts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReceptionistProfileRequest {
    private Shifts shift;
}
