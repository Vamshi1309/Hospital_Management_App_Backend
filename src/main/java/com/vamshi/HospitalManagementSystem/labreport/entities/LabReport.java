package com.vamshi.HospitalManagementSystem.labreport.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.vamshi.HospitalManagementSystem.appointment.entities.AppointmentEntity;
import com.vamshi.HospitalManagementSystem.common.enums.XRayType;
import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lab_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LabReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Patient
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patient;

    // Radiologist
    @ManyToOne
    @JoinColumn(name = "radiologist_id", nullable = false)
    private UserEntity radiologist;

    // Related appointment
    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentEntity appointment;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type", nullable = false)
    private XRayType reportType;

    // File URL / S3 URL / local path
    @Column(name = "report_url", nullable = false)
    private String reportUrl;

    @Column(columnDefinition = "TEXT")
    private String findings;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    private void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}