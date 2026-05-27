package com.vamshi.HospitalManagementSystem.appointment.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vamshi.HospitalManagementSystem.appointment.dtos.AppointmentResponse;
import com.vamshi.HospitalManagementSystem.appointment.dtos.CreateAppointmentRequest;
import com.vamshi.HospitalManagementSystem.appointment.dtos.UpdateAppointmentStatusRequest;
import com.vamshi.HospitalManagementSystem.appointment.services.AppointmentService;
import com.vamshi.HospitalManagementSystem.common.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<ApiResponse<AppointmentResponse>> createAppointment(
            @RequestBody @Valid CreateAppointmentRequest request) {

        AppointmentResponse response = appointmentService.createAppointment(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Appointment Created Successfully", response));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAppointments() {

        List<AppointmentResponse> response = appointmentService.getAllAppointments();

        return ResponseEntity.ok(ApiResponse.success("Appointments Fetched Successfully", response));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAppointmentsByPatient(@PathVariable UUID patientId) {
        List<AppointmentResponse> response = appointmentService.getAppointmentsByPatient(patientId);

        return ResponseEntity
                .ok(ApiResponse.success("Appointments Fetched Successfully With Patient Id " + patientId, response));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAppointmentsByDoctor(@PathVariable UUID doctorId) {
        List<AppointmentResponse> response = appointmentService.getAppointmentsByDoctor(doctorId);

        return ResponseEntity
                .ok(ApiResponse.success("Appointments Fetched Successfully With Doctor Id" + doctorId, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentResponse>> getAppointmentsById(@PathVariable UUID id) {
        AppointmentResponse response = appointmentService.getAppointmentById(id);

        return ResponseEntity
                .ok(ApiResponse.success("Appointment Fetched Successfully With Appointment Id" + id, response));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AppointmentResponse>> updateStatus(@PathVariable UUID id,
            @Valid @RequestBody UpdateAppointmentStatusRequest status) {
        AppointmentResponse response = appointmentService.updateAppointmentStatus(id, status);

        return ResponseEntity
                .ok(ApiResponse.success("Updated Appointment Status Successfully", response));
    }
}
