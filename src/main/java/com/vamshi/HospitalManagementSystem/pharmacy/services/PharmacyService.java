package com.vamshi.HospitalManagementSystem.pharmacy.services;

import com.vamshi.HospitalManagementSystem.pharmacy.dtos.DispenseMedicineRequest;
import com.vamshi.HospitalManagementSystem.pharmacy.dtos.DispenseResponse;

import java.util.List;
import java.util.UUID;

public interface PharmacyService {

    DispenseResponse dispenseMedicine(
            DispenseMedicineRequest request);

    List<DispenseResponse> getDispenseHistoryByPatient(
            UUID patientId);

    List<DispenseResponse> getDispenseHistoryByPharmacist(
            UUID pharmacistId);

    List<DispenseResponse> getDispenseHistoryByPrescription(
            UUID prescriptionId);
}