package com.vamshi.HospitalManagementSystem.inventory.controllers;

import com.vamshi.HospitalManagementSystem.common.ApiResponse;
import com.vamshi.HospitalManagementSystem.inventory.dtos.AddMedicineRequest;
import com.vamshi.HospitalManagementSystem.inventory.dtos.ConsumeMedicineRequest;
import com.vamshi.HospitalManagementSystem.inventory.dtos.MedicineResponse;
import com.vamshi.HospitalManagementSystem.inventory.dtos.UpdateMedicineRequest;
import com.vamshi.HospitalManagementSystem.inventory.services.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

        private final InventoryService inventoryService;

        // POST /api/inventory
        @PostMapping
        public ResponseEntity<ApiResponse<MedicineResponse>> addMedicine(
                        @RequestBody @Valid AddMedicineRequest request) {

                MedicineResponse response = inventoryService
                                .addMedicine(request);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(
                                                "Medicine added successfully", response));
        }

        // PUT /api/inventory/{id}
        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<MedicineResponse>> updateMedicine(
                        @PathVariable UUID id,
                        @RequestBody @Valid UpdateMedicineRequest request) {

                MedicineResponse response = inventoryService
                                .updateMedicine(id, request);

                return ResponseEntity.ok(
                                ApiResponse.success(
                                                "Medicine updated successfully", response));
        }

        // GET /api/inventory/{id}
        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<MedicineResponse>> getMedicineById(
                        @PathVariable UUID id) {

                MedicineResponse response = inventoryService
                                .getMedicineById(id);

                return ResponseEntity.ok(
                                ApiResponse.success(
                                                "Medicine fetched successfully", response));
        }

        // GET /api/inventory
        @GetMapping
        public ResponseEntity<ApiResponse<List<MedicineResponse>>> getAllMedicines() {

                List<MedicineResponse> response = inventoryService
                                .getAllMedicines();

                return ResponseEntity.ok(
                                ApiResponse.success(
                                                "Medicines fetched successfully", response));
        }

        // GET /api/inventory/low-stock?threshold=10
        @GetMapping("/low-stock")
        public ResponseEntity<ApiResponse<List<MedicineResponse>>> getLowStockMedicines(
                        @RequestParam(defaultValue = "10") Integer threshold) {

                List<MedicineResponse> response = inventoryService
                                .getLowStockMedicines(threshold);

                return ResponseEntity.ok(
                                ApiResponse.success(
                                                "Low stock medicines fetched", response));
        }

        // GET /api/inventory/expired
        @GetMapping("/expired")
        public ResponseEntity<ApiResponse<List<MedicineResponse>>> getExpiredMedicines() {

                List<MedicineResponse> response = inventoryService
                                .getExpiredMedicines();

                return ResponseEntity.ok(
                                ApiResponse.success(
                                                "Expired medicines fetched", response));
        }

        @PatchMapping("/{id}/consume")
        public ResponseEntity<ApiResponse<MedicineResponse>> consumeMedicine(
                        @PathVariable UUID id,
                        @RequestBody @Valid ConsumeMedicineRequest request) {

                MedicineResponse response = inventoryService.consumeMedicine(
                                id,
                                request.getQuantity());

                return ResponseEntity.ok(
                                ApiResponse.success(
                                                "Medicine stock updated",
                                                response));
        }

        

        // DELETE /api/inventory/{id}
        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteMedicine(
                        @PathVariable UUID id) {

                inventoryService.deleteMedicine(id);

                return ResponseEntity.ok(
                                ApiResponse.success("Medicine deleted successfully"));
        }
}