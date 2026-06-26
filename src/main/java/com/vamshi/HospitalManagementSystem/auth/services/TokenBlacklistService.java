package com.vamshi.HospitalManagementSystem.auth.services;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {
    private final RedisTemplate<String, String> redisTemplate;

    private static final String PREFIX = "blacklist:";

    public void blacklistToken(String token, long expirationMillis) {
        long expirySeconds = expirationMillis / 1000;

        redisTemplate.opsForValue().set(
                PREFIX + token,
                "true",
                expirySeconds,
                TimeUnit.SECONDS);
    }

    public boolean isBlackListed(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(PREFIX + token));
    }
}
