package com.vamshi.HospitalManagementSystem.pharmacy.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.vamshi.HospitalManagementSystem.inventory.entities.MedicineEntity;
import com.vamshi.HospitalManagementSystem.prescription.entities.PrescriptionEntity;
import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dispense_records")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private PrescriptionEntity prescription;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patient;

    @ManyToOne
    @JoinColumn(name = "dispensed_by_id", nullable = false)
    private UserEntity dispensedBy;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private MedicineEntity medicine;

    @Column(nullable = false)
    private Integer quantityDispensed;

    @Column(nullable = false)
    private Double totalAmount;

    @CreationTimestamp
    private LocalDateTime dispensedAt;

    private String notes;
}
