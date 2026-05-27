package com.vamshi.HospitalManagementSystem.labreport.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vamshi.HospitalManagementSystem.common.ApiResponse;
import com.vamshi.HospitalManagementSystem.labreport.dtos.CreateLabReportRequest;
import com.vamshi.HospitalManagementSystem.labreport.dtos.LabReportResponse;
import com.vamshi.HospitalManagementSystem.labreport.services.LabReportService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lab-reports")
@RequiredArgsConstructor
public class LabReportController {
    private final LabReportService labReportService;

    @PostMapping()
    public ResponseEntity<ApiResponse<LabReportResponse>> createLabReport(
            @Valid @RequestBody CreateLabReportRequest request) {
        LabReportResponse response = labReportService.createLabReport(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Lab report created successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LabReportResponse>> getLabReportById(@PathVariable UUID id) {
        LabReportResponse response = labReportService.getLabReportById(id);

        return ResponseEntity.ok(ApiResponse.success("Fetched lab report successfully", response));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ApiResponse<List<LabReportResponse>>> getLabReportByPatientId(@PathVariable UUID patientId) {
        List<LabReportResponse> response = labReportService.getLabReportByPatientId(patientId);

        return ResponseEntity.ok(ApiResponse.success("Fetched lab reports successfully", response));
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<ApiResponse<List<LabReportResponse>>> getLabReportByAppointmentId(@PathVariable UUID appointmentId) {
        List<LabReportResponse> response = labReportService.getLabReportByAppointmentId(appointmentId);

        return ResponseEntity.ok(ApiResponse.success("Fetched lab reports successfully", response));
    }

}
