package com.vamshi.HospitalManagementSystem.auth.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vamshi.HospitalManagementSystem.auth.services.TokenBlacklistService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

        private final JwtUtil jwtUtil;
        private final UserDetailsServiceImpl userService;
        private final TokenBlacklistService tokenBlacklistService;

        @Override
        protected void doFilterInternal(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        FilterChain filterChain)
                        throws ServletException, IOException {

                final String authHeader = request.getHeader("Authorization");

                String token = null;
                String phoneNumber = null;

                if (authHeader != null
                                && authHeader.startsWith("Bearer ")) {

                        token = authHeader.substring(7);

                        if (tokenBlacklistService.isBlackListed(token)) {
                                filterChain.doFilter(request, response);
                                return; 
                        }

                        phoneNumber = jwtUtil.extractPhoneNumber(token);
                }

                if (phoneNumber != null
                                && SecurityContextHolder
                                                .getContext()
                                                .getAuthentication() == null) {

                        UserDetails userDetails = userService.loadUserByUsername(
                                        phoneNumber);

                        if (jwtUtil.validateToken(
                                        token,
                                        userDetails)) {

                                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                                userDetails,
                                                null,
                                                userDetails.getAuthorities());

                                auth.setDetails(
                                                new WebAuthenticationDetailsSource()
                                                                .buildDetails(request));

                                SecurityContextHolder
                                                .getContext()
                                                .setAuthentication(auth);
                        }
                }

                filterChain.doFilter(request, response);
        }
}