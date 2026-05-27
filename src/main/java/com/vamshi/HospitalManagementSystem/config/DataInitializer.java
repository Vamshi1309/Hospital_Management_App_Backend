package com.vamshi.HospitalManagementSystem.config;

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

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByphoneNumber("9999999999")) {
            UserEntity admin = UserEntity.builder()
                    .name("Super Admin")
                    .phoneNumber("9999999999")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("admin@123"))
                    .role(Role.ADMIN)
                    .isActive(true)
                    .build();

            userRepository.save(admin);

            log.info("✅ Admin created → phone: 9999999999 | password: admin@123");
        } else {
            log.info("✅ Admin already exists — skipping creation");
        }
    }

}
