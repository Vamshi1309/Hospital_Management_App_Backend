package com.vamshi.HospitalManagementSystem.receptionist.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamshi.HospitalManagementSystem.receptionist.entities.ReceptionistProfile;

@Repository
public interface ReceptionistProfileRepository extends JpaRepository<ReceptionistProfile, UUID> {
    Optional<ReceptionistProfile> findByUserId(UUID userId);
}
