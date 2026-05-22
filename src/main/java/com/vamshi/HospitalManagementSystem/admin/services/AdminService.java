package com.vamshi.HospitalManagementSystem.admin.services;

import java.util.List;
import java.util.UUID;

import com.vamshi.HospitalManagementSystem.admin.dtos.AssignRoleRequest;
import com.vamshi.HospitalManagementSystem.admin.dtos.CreateUserRequest;
import com.vamshi.HospitalManagementSystem.admin.dtos.UpdateUserStatusRequest;
import com.vamshi.HospitalManagementSystem.admin.dtos.UserSummaryResponse;

public interface AdminService {
    UserSummaryResponse createUser(CreateUserRequest request);

    List<UserSummaryResponse> getAllUsers();

    UserSummaryResponse getUserById(UUID id);

    UserSummaryResponse updateStatus(UUID id, UpdateUserStatusRequest request);

    UserSummaryResponse assignRole(UUID id, AssignRoleRequest request);
}
