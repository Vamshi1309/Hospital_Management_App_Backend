package com.vamshi.HospitalManagementSystem.pharmacy.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamshi.HospitalManagementSystem.pharmacy.entities.DispenseEntity;

@Repository
public interface DispenseRepository
        extends JpaRepository<DispenseEntity, UUID> {

    List<DispenseEntity> findByPatientIdOrderByDispensedAtDesc(
            UUID patientId);

    List<DispenseEntity> findByDispensedByIdOrderByDispensedAtDesc(
            UUID pharmacistId);

    List<DispenseEntity> findByPrescriptionIdOrderByDispensedAtDesc(
            UUID prescriptionId);

    boolean existsByPrescriptionId(UUID prescriptionId);
}
