package com.vamshi.HospitalManagementSystem.admin.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vamshi.HospitalManagementSystem.admin.dtos.AssignRoleRequest;
import com.vamshi.HospitalManagementSystem.admin.dtos.CreateUserRequest;
import com.vamshi.HospitalManagementSystem.admin.dtos.UpdateUserStatusRequest;
import com.vamshi.HospitalManagementSystem.admin.dtos.UserSummaryResponse;
import com.vamshi.HospitalManagementSystem.doctor.entities.DoctorProfileEntity;
import com.vamshi.HospitalManagementSystem.doctor.repositories.DoctorProfileRepository;
import com.vamshi.HospitalManagementSystem.exceptions.ResourceAlreadyExistsException;
import com.vamshi.HospitalManagementSystem.exceptions.ResourceNotFoundException;
import com.vamshi.HospitalManagementSystem.pharmacist.entities.PharmacistProfileEntity;
import com.vamshi.HospitalManagementSystem.pharmacist.repositories.PharmacistProfileRepository;
import com.vamshi.HospitalManagementSystem.radiologist.entities.RadiologistEntity;
import com.vamshi.HospitalManagementSystem.radiologist.repositories.RadiologistProfileRepository;
import com.vamshi.HospitalManagementSystem.receptionist.entities.ReceptionistProfile;
import com.vamshi.HospitalManagementSystem.receptionist.repositories.ReceptionistProfileRepository;
import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;
import com.vamshi.HospitalManagementSystem.user.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final DoctorProfileRepository doctorProfileRepository;
        private final ReceptionistProfileRepository receptionistProfileRepository;
        private final PharmacistProfileRepository pharmacistProfileRepository;
        private final RadiologistProfileRepository radiologistProfileRepository;

        @Override
        @Transactional
        public UserSummaryResponse createUser(CreateUserRequest request) {
                if (userRepository.existsByphoneNumber(request.getPhoneNumber())) {
                        throw new ResourceAlreadyExistsException(
                                        "Phone Number Already Exists");
                }

                UserEntity user = UserEntity.builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .phoneNumber(request.getPhoneNumber())
                                .role(request.getRole())
                                .isActive(true)
                                .build();

                UserEntity savedUser = userRepository.save(user);

                switch (savedUser.getRole()) {
                        case DOCTOR -> doctorProfileRepository.save(
                                        DoctorProfileEntity.builder()
                                                        .user(savedUser)
                                                        .build());

                        case RECEPTIONIST -> receptionistProfileRepository.save(
                                        ReceptionistProfile.builder()
                                                        .user(savedUser)
                                                        .build());

                        case PHARMACIST -> pharmacistProfileRepository.save(
                                        PharmacistProfileEntity.builder()
                                                        .user(savedUser)
                                                        .build());

                        case RADIOLOGIST -> radiologistProfileRepository.save(
                                        RadiologistEntity.builder()
                                                        .user(savedUser)
                                                        .build());

                        default -> {

                        }
                }

                return mapToResponse(savedUser);
        }

        @Override
        public List<UserSummaryResponse> getAllUsers() {
                return userRepository.findAll()
                                .stream()
                                .map(this::mapToResponse)
                                .collect(Collectors.toList());
        }

        @Override
        public UserSummaryResponse getUserById(UUID id) {
                UserEntity user = userRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));
                return mapToResponse(user);
        }

        @Override
        public UserSummaryResponse updateStatus(UUID id, UpdateUserStatusRequest request) {
                UserEntity user = userRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));

                user.setActive(request.getIsActive());

                return mapToResponse(user);
        }

        @Override
        public UserSummaryResponse assignRole(UUID id, AssignRoleRequest request) {
                UserEntity user = userRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "User not found with id: " + id));
                user.setRole(request.getRole());
                return mapToResponse(userRepository.save(user));
        }

        private UserSummaryResponse mapToResponse(UserEntity user) {
                return UserSummaryResponse.builder()
                                .userId(user.getId())
                                .name(user.getName())
                                .email(user.getEmail())
                                .phoneNumber(user.getPhoneNumber())
                                .role(user.getRole())
                                .isActive(user.isActive())
                                .createdAt(user.getCreatedAt())
                                .build();
        }

}
