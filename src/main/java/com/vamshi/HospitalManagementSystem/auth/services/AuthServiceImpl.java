package com.vamshi.HospitalManagementSystem.auth.services;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vamshi.HospitalManagementSystem.auth.dto.AuthResponse;
import com.vamshi.HospitalManagementSystem.auth.dto.LoginRequest;
import com.vamshi.HospitalManagementSystem.auth.dto.RegisterRequest;
import com.vamshi.HospitalManagementSystem.auth.security.JwtUtil;
import com.vamshi.HospitalManagementSystem.common.enums.Role;
import com.vamshi.HospitalManagementSystem.exceptions.ResourceAlreadyExistsException;
import com.vamshi.HospitalManagementSystem.exceptions.ResourceNotFoundException;
import com.vamshi.HospitalManagementSystem.patient.entities.PatientProfileEntity;
import com.vamshi.HospitalManagementSystem.patient.repositories.PatientProfileRepository;
import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;
import com.vamshi.HospitalManagementSystem.user.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final PatientProfileRepository patientProfileRepository;

    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByphoneNumber(request.getPhoneNumber())) {
            throw new ResourceAlreadyExistsException("Phone Number Already Exists");
        }

        UserEntity user = new UserEntity();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(Role.PATIENT);

        UserEntity savedUser = userRepository.save(user);

        PatientProfileEntity patientProfile = PatientProfileEntity
                .builder()
                .user(savedUser)
                .build();

        patientProfileRepository.save(patientProfile);

        UserDetails userDetails = new User(
                savedUser.getPhoneNumber(),
                savedUser.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(savedUser.getRole().name())));

        String token = jwtUtil.generateToken(userDetails);

        AuthResponse resp = new AuthResponse();

        resp.setToken(token);
        resp.setId(savedUser.getId());
        resp.setRole(savedUser.getRole());
        resp.setName(savedUser.getName());

        return resp;
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));

        UserEntity user = userRepository.findByPhoneNumber(
                request.getPhoneNumber()).orElseThrow(() -> new ResourceNotFoundException("User not exist"));

        UserDetails userDetails = new User(
                user.getPhoneNumber(),
                user.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(user.getRole().name())));

        String token = jwtUtil.generateToken(userDetails);

        AuthResponse response = new AuthResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setRole(user.getRole());
        response.setToken(token);

        return response;
    }
}
