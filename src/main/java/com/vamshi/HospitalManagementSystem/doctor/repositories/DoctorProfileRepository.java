package com.vamshi.HospitalManagementSystem.doctor.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamshi.HospitalManagementSystem.doctor.entities.DoctorProfileEntity;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfileEntity, UUID> {
    Optional<DoctorProfileEntity> findByUserId(UUID userId);
}
