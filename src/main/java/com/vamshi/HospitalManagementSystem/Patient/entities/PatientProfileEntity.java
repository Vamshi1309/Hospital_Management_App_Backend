package com.vamshi.HospitalManagementSystem.Patient.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patient_profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    private LocalDate dateOfBirth;

    private String bloodGroup;
}
