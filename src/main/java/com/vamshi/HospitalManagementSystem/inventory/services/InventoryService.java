package com.vamshi.HospitalManagementSystem.inventory.services;

import com.vamshi.HospitalManagementSystem.inventory.dtos.AddMedicineRequest;
import com.vamshi.HospitalManagementSystem.inventory.dtos.MedicineResponse;
import com.vamshi.HospitalManagementSystem.inventory.dtos.UpdateMedicineRequest;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    MedicineResponse addMedicine(AddMedicineRequest request);

    MedicineResponse updateMedicine(UUID id,
            UpdateMedicineRequest request);

    MedicineResponse getMedicineById(UUID id);

    List<MedicineResponse> getAllMedicines();

    List<MedicineResponse> getLowStockMedicines(Integer threshold);

    List<MedicineResponse> getExpiredMedicines();

    MedicineResponse consumeMedicine(
            UUID id,
            Integer quantity);

    void deleteMedicine(UUID id);
}