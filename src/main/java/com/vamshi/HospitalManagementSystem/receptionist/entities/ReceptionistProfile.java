package com.vamshi.HospitalManagementSystem.receptionist.entities;

import java.util.UUID;

import com.vamshi.HospitalManagementSystem.common.enums.Shifts;
import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "receptionist_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceptionistProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Shifts Shift;
}
