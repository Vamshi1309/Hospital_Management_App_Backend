package com.vamshi.HospitalManagementSystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vamshi.HospitalManagementSystem.common.enums.Role;
import com.vamshi.HospitalManagementSystem.user.entities.UserEntity;
import com.vamshi.HospitalManagementSystem.user.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.phone}")
    private String adminPhone;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {

        if (!userRepository.existsByphoneNumber(adminPhone)) {

            UserEntity admin = UserEntity.builder()
                    .name("Super Admin")
                    .phoneNumber(adminPhone)
                    .email("admin@hospital.com")
                    .password(passwordEncoder.encode(adminPassword))
                    .role(Role.ADMIN)
                    .isActive(true)
                    .build();

            userRepository.save(admin);

            log.info("✅ Admin created → phone: {}",
                    adminPhone);
        } else {
            log.info("✅ Admin already exists — skipping");
        }
    }
}
