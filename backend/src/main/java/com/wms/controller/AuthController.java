package com.wms.controller;

import com.wms.dto.*;
import com.wms.entity.User;
import com.wms.service.UserService;
import com.wms.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDTO signupRequest) {
        try {
            User user = userService.createUser(
                    signupRequest.getEmail(),
                    signupRequest.getFirstName(),
                    signupRequest.getLastName(),
                    signupRequest.getPassword(),
                    signupRequest.getPhoneNumber()
            );

            String token = tokenProvider.generateToken(user.getId());
            String refreshToken = tokenProvider.generateRefreshToken(user.getId());

            UserDTO userDTO = convertToDTO(user);
            AuthResponseDTO response = AuthResponseDTO.builder()
                    .accessToken(token)
                    .refreshToken(refreshToken)
                    .user(userDTO)
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Signup failed: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        try {
            User user = userService.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            userService.updateLastLogin(user.getId());

            String token = tokenProvider.generateToken(user.getId());
            String refreshToken = tokenProvider.generateRefreshToken(user.getId());

            UserDTO userDTO = convertToDTO(user);
            AuthResponseDTO response = AuthResponseDTO.builder()
                    .accessToken(token)
                    .refreshToken(refreshToken)
                    .user(userDTO)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Login failed: " + e.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDTO request) {
        try {
            if (tokenProvider.validateRefreshToken(request.getRefreshToken())) {
                Long userId = tokenProvider.getUserIdFromToken(request.getRefreshToken());
                String newToken = tokenProvider.generateToken(userId);

                AuthResponseDTO response = AuthResponseDTO.builder()
                        .accessToken(newToken)
                        .refreshToken(request.getRefreshToken())
                        .build();

                return ResponseEntity.ok(response);
            }
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid refresh token"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Token refresh failed: " + e.getMessage()));
        }
    }

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .profilePictureUrl(user.getProfilePictureUrl())
                .isActive(user.getIsActive())
                .isEmailVerified(user.getIsEmailVerified())
                .isPhoneVerified(user.getIsPhoneVerified())
                .role(user.getRole().toString())
                .lastLoginAt(user.getLastLoginAt())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
