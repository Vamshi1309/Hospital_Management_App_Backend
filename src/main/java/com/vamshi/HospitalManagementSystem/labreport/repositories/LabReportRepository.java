package com.vamshi.HospitalManagementSystem.labreport.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamshi.HospitalManagementSystem.common.enums.ReportType;
import com.vamshi.HospitalManagementSystem.labreport.entities.LabReportEntity;

@Repository
public interface LabReportRepository extends JpaRepository<LabReportEntity, UUID> {

    boolean existsByAppointmentId(UUID appointmentId);

    List<LabReportEntity> findByPatientId(UUID patientId);

    List<LabReportEntity> findByRadiologistId(UUID radiologistId);

    List<LabReportEntity> findByAppointmentId(UUID appointmentId);

    List<LabReportEntity> findByPatientIdAndReportType(UUID patientId, ReportType type);
}
