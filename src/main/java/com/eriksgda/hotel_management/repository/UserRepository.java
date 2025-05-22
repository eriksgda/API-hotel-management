package com.eriksgda.hotel_management.repository;

import com.eriksgda.hotel_management.entity.User;
import com.eriksgda.hotel_management.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findFirstByEmail(String email);
    Optional<User> findByUserRole(UserRole role);
}
