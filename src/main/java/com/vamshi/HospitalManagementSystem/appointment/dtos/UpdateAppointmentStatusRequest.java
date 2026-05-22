package com.vamshi.HospitalManagementSystem.appointment.dtos;

import com.vamshi.HospitalManagementSystem.common.enums.AppointmentStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAppointmentStatusRequest {
    @NotNull(message = "Appointemnet status is mandatory")
    private AppointmentStatus status;
}
