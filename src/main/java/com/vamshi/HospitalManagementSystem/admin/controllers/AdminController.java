package com.vamshi.HospitalManagementSystem.admin.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vamshi.HospitalManagementSystem.admin.dtos.AssignRoleRequest;
import com.vamshi.HospitalManagementSystem.admin.dtos.CreateUserRequest;
import com.vamshi.HospitalManagementSystem.admin.dtos.UpdateUserStatusRequest;
import com.vamshi.HospitalManagementSystem.admin.dtos.UserSummaryResponse;
import com.vamshi.HospitalManagementSystem.admin.services.AdminService;
import com.vamshi.HospitalManagementSystem.common.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/users")
    public ResponseEntity<ApiResponse<UserSummaryResponse>> createUser(
            @RequestBody @Valid CreateUserRequest request) {
        UserSummaryResponse response = adminService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User Created Successfully ", response));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserSummaryResponse>>> getAllUsers() {

        List<UserSummaryResponse> users = adminService.getAllUsers();

        return ResponseEntity.ok(ApiResponse.success("Users Fetched Successfully", users));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<UserSummaryResponse>> getUserById(@PathVariable UUID id) {
        UserSummaryResponse user = adminService.getUserById(id);

        return ResponseEntity.ok(ApiResponse.success("User Fetched successfully with Id:" + id, user));
    }

    @PatchMapping("/users/{id}/role")
    public ResponseEntity<ApiResponse<UserSummaryResponse>> assignRole(@PathVariable UUID id,
            @RequestBody @Valid AssignRoleRequest role) {
        UserSummaryResponse response = adminService.assignRole(id, role);
        return ResponseEntity.ok(
                ApiResponse.success("Role updated successfully", response));
    }

    @PatchMapping("/users/{id}/status")
    public ResponseEntity<ApiResponse<UserSummaryResponse>> updateStatus(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserStatusRequest request) {

        UserSummaryResponse response = adminService.updateStatus(id, request);
        return ResponseEntity.ok(
                ApiResponse.success("Status updated successfully", response));
    }
}
