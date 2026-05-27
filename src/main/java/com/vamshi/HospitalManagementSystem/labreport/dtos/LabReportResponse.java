package com.vamshi.HospitalManagementSystem.labreport.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LabReportResponse {

    private UUID reportId;
    private UUID appointmentId;
    private UUID patientId;
    private String patientName;
    private UUID radiologistId;
    private String radiologistName;
    private String reportType;
    private String reportUrl;
    private String findings;
    private LocalDateTime createdAt;
}
