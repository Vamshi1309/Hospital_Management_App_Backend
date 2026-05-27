package com.vamshi.HospitalManagementSystem.radiologist.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamshi.HospitalManagementSystem.radiologist.entities.RadiologistEntity;

@Repository
public interface RadiologistProfileRepository extends JpaRepository<RadiologistEntity, UUID> {
    Optional<RadiologistEntity> findByUserId(UUID userId);
}
