package com.vamshi.HospitalManagementSystem.pharmacist.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;

@Entity
@Table(name = "pharmacist_profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharmacistProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    private String licenseNumber;
}