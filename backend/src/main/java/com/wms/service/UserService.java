package com.wms.service;

import com.wms.entity.User;
import com.wms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(String email, String firstName, String lastName, String password, String phoneNumber) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        if (phoneNumber != null && userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new RuntimeException("Phone number already exists");
        }

        User user = User.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .passwordHash(passwordEncoder.encode(password))
                .isActive(true)
                .isEmailVerified(false)
                .isPhoneVerified(false)
                .role(User.UserRole.WAREHOUSE_OWNER)
                .build();

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, String firstName, String lastName, String phoneNumber, String profilePictureUrl) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (firstName != null) user.setFirstName(firstName);
        if (lastName != null) user.setLastName(lastName);
        if (phoneNumber != null && !phoneNumber.equals(user.getPhoneNumber())) {
            if (userRepository.existsByPhoneNumber(phoneNumber)) {
                throw new RuntimeException("Phone number already exists");
            }
            user.setPhoneNumber(phoneNumber);
        }
        if (profilePictureUrl != null) user.setProfilePictureUrl(profilePictureUrl);
        return userRepository.save(user);
    }

    public void verifyEmail(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsEmailVerified(true);
        userRepository.save(user);
    }

    public void updateLastLogin(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
