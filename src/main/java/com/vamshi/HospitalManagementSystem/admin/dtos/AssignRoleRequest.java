package com.vamshi.HospitalManagementSystem.admin.dtos;

import com.vamshi.HospitalManagementSystem.common.enums.Role;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignRoleRequest {

    @NotNull(message = "Role is required")
    private Role role;
}
