package com.vamshi.HospitalManagementSystem.pharmacist.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamshi.HospitalManagementSystem.pharmacist.entities.PharmacistProfileEntity;

@Repository
public interface PharmacistProfileRepository extends JpaRepository<PharmacistProfileEntity, UUID> {
    Optional<PharmacistProfileEntity> findByUserId(UUID userId);
}
