package com.vamshi.HospitalManagementSystem.inventory.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vamshi.HospitalManagementSystem.inventory.entities.MedicineEntity;

public interface InventoryRepository extends JpaRepository<MedicineEntity, UUID> {

    Optional<MedicineEntity> findByMedicineName(String medicineName);

    boolean existsByMedicineName(String medicineName);

    List<MedicineEntity> findByQuantityLessThan(Integer quantity);

    List<MedicineEntity> findByExpiryDateBefore(LocalDate date);
}
