package com.vamshi.HospitalManagementSystem.radiologist.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamshi.HospitalManagementSystem.radiologist.entities.RadiologistProfile;

@Repository
public interface RadiologistProfileRepository extends JpaRepository<RadiologistProfile, UUID> {
    Optional<RadiologistProfile> findByUserId(UUID userId);
}
