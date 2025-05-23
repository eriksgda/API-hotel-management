package com.eriksgda.hotel_management.model;

import com.eriksgda.hotel_management.entity.User;
import com.eriksgda.hotel_management.enums.UserRole;

import java.util.UUID;

public record SignupResponseDTO(UUID id, String email, String name, UserRole role) {
    public static SignupResponseDTO fromEntity(User user) {
        return new SignupResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getUserRole()
        );
    }
}
