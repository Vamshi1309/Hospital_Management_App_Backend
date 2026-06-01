package com.vamshi.HospitalManagementSystem.patient.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamshi.HospitalManagementSystem.patient.entities.PatientProfileEntity;

@Repository
public interface PatientProfileRepository
        extends JpaRepository<PatientProfileEntity, UUID> {

    Optional<PatientProfileEntity> findByUserId(UUID userId);
}
