package com.vamshi.HospitalManagementSystem.appointment.services;

import java.util.List;
import java.util.UUID;

import com.vamshi.HospitalManagementSystem.appointment.dtos.AppointmentResponse;
import com.vamshi.HospitalManagementSystem.appointment.dtos.CreateAppointmentRequest;
import com.vamshi.HospitalManagementSystem.appointment.dtos.UpdateAppointmentStatusRequest;

public interface AppointmentService {

    public AppointmentResponse createAppointment(CreateAppointmentRequest request);

    public List<AppointmentResponse> getAllAppointments();

    public List<AppointmentResponse> getAppointmentsByPatient(UUID patientId);

    public List<AppointmentResponse> getAppointmentsByDoctor(UUID doctorId);

    AppointmentResponse updateAppointmentStatus(UUID appointmentId, UpdateAppointmentStatusRequest request);

    AppointmentResponse getAppointmentById(UUID id);
}
