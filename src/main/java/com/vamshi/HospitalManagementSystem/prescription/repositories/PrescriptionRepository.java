package com.vamshi.HospitalManagementSystem.prescription.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamshi.HospitalManagementSystem.prescription.entities.PrescriptionEntity;

@Repository
public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, UUID> {
    List<PrescriptionEntity> findByPatientId(UUID patientId);

    List<PrescriptionEntity> findByDoctorId(UUID doctorId);

    Optional<PrescriptionEntity> findByAppointmentId(UUID appointmentId);

    boolean existsByAppointmentId(UUID appointmentId);
}
