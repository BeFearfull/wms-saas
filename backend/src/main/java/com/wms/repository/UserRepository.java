package com.wms.repository;

import com.wms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByGoogleId(String googleId);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
