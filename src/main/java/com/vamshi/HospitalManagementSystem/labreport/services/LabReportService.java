package com.vamshi.HospitalManagementSystem.labreport.services;

import java.util.List;
import java.util.UUID;

import com.vamshi.HospitalManagementSystem.labreport.dtos.CreateLabReportRequest;
import com.vamshi.HospitalManagementSystem.labreport.dtos.LabReportResponse;

public interface LabReportService {

    LabReportResponse createLabReport(CreateLabReportRequest request);

    LabReportResponse getLabReportById(UUID id);

    List<LabReportResponse> getLabReportByPatientId(UUID patientId);

    List<LabReportResponse> getLabReportByAppointmentId(UUID appointmentId);
}
