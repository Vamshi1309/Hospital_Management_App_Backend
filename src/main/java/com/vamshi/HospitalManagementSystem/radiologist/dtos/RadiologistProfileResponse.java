package com.vamshi.HospitalManagementSystem.radiologist.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RadiologistProfileResponse {

    private UUID userId;

    private String name;

    private String email;

    private String phoneNumber;

    private String labSection;

    private String certification;
}
