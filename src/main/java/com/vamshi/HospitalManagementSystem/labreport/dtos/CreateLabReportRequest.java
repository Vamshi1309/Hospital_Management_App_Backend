package com.vamshi.HospitalManagementSystem.labreport.dtos;

import java.util.UUID;

import com.vamshi.HospitalManagementSystem.common.enums.ReportType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLabReportRequest {

    @NotNull(message = "appointmentId is mandatory")
    private UUID appointmentId;

    @NotNull(message = "Report type is required")
    private ReportType reportType;

    private String findings;
}
