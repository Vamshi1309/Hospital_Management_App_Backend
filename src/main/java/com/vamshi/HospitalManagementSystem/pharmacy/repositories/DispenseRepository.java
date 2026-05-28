package com.vamshi.HospitalManagementSystem.pharmacy.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamshi.HospitalManagementSystem.pharmacy.entities.DispenseEntity;

@Repository
public interface DispenseRepository extends JpaRepository<DispenseEntity, UUID> {
    List<DispenseEntity> findByPatientId(UUID patientId);

    List<DispenseEntity> findByDispensedById(UUID pharmacistId);

    List<DispenseEntity> findByPrescriptionId(UUID prescriptionId);

    boolean existsByPrescriptionId(UUID prescriptionId);
}
