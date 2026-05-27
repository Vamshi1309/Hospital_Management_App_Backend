package com.vamshi.HospitalManagementSystem.prescription.controllers;

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
import com.vamshi.HospitalManagementSystem.prescription.dtos.CreatePrescriptionRequest;
import com.vamshi.HospitalManagementSystem.prescription.dtos.PrescriptionResponse;
import com.vamshi.HospitalManagementSystem.prescription.services.PrescriptionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping()
    public ResponseEntity<ApiResponse<PrescriptionResponse>> createPrescription(
            @Valid @RequestBody CreatePrescriptionRequest request) {
        PrescriptionResponse response = prescriptionService.createPrescription(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Prescription Created Successfully ", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PrescriptionResponse>> getPrescriptionById(@PathVariable UUID id) {
        PrescriptionResponse response = prescriptionService.getPrescriptionById(id);

        return ResponseEntity.ok(ApiResponse.success("Fetched Prescription by id Successfully", response));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ApiResponse<List<PrescriptionResponse>>> getPrescriptionByPatientId(
            @PathVariable UUID patientId) {
        List<PrescriptionResponse> response = prescriptionService.getPrescriptionsByPatient(patientId);

        return ResponseEntity.ok(ApiResponse.success("Fetched Prescriptions by Patient id Successfully", response));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ApiResponse<List<PrescriptionResponse>>> getPrescriptionByDoctorId(
            @PathVariable UUID doctorId) {
        List<PrescriptionResponse> response = prescriptionService.getPrescriptionsByDoctor(doctorId);

        return ResponseEntity.ok(ApiResponse.success("Fetched Prescriptions by Doctor id Successfully", response));
    }

}
