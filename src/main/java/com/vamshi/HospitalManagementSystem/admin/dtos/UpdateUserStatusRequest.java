package com.vamshi.HospitalManagementSystem.admin.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserStatusRequest {

    @NotNull(message = "status is required")
    private Boolean isActive;
}
