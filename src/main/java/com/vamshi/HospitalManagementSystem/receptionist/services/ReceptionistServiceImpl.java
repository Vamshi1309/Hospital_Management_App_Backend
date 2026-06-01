package com.vamshi.HospitalManagementSystem.receptionist.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vamshi.HospitalManagementSystem.exceptions.ResourceNotFoundException;
import com.vamshi.HospitalManagementSystem.receptionist.dtos.ReceptionistProfileResponse;
import com.vamshi.HospitalManagementSystem.receptionist.dtos.UpdateReceptionistProfileRequest;
import com.vamshi.HospitalManagementSystem.receptionist.entities.ReceptionistProfile;
import com.vamshi.HospitalManagementSystem.receptionist.repositories.ReceptionistProfileRepository;
import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;
import com.vamshi.HospitalManagementSystem.user.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceptionistServiceImpl implements ReceptionistService {

        private final UserRepository userRepository;

        private final ReceptionistProfileRepository receptionistRepository;

        @Override
        public ReceptionistProfileResponse getMyProfile() {
                String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();

                UserEntity user = userRepository.findByPhoneNumber(phoneNumber)
                                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

                ReceptionistProfile receptionist = receptionistRepository.findByUserId(user.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("Receptionist not found"));

                return mapToResponse(receptionist);
        }

        @Override
        public ReceptionistProfileResponse updateMyProfile(UpdateReceptionistProfileRequest request) {
                String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();

                UserEntity user = userRepository.findByPhoneNumber(phoneNumber)
                                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

                ReceptionistProfile receptionist = receptionistRepository.findByUserId(user.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("Receptionist not found"));

                if (request.getShift() != null) {
                        receptionist.setShift(request.getShift());
                }

                ReceptionistProfile saved = receptionistRepository
                                .save(receptionist);

                return mapToResponse(saved);
        }

        private ReceptionistProfileResponse mapToResponse(ReceptionistProfile receptionist) {
                return ReceptionistProfileResponse.builder()
                                .userId(receptionist.getUser().getId())
                                .name(receptionist.getUser().getName())
                                .email(receptionist.getUser().getEmail())
                                .phoneNumber(receptionist.getUser().getPhoneNumber())
                                .shift(receptionist.getShift() != null
                                                ? receptionist.getShift().name()
                                                : null)
                                .build();
        }
}
